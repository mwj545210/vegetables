package com.mwj.core.utils;

import org.apache.log4j.Logger;

import java.io.File;

public class SFtpTool {
	
	  public static Logger logger = Logger.getLogger(SFtpTool.class);
      /**
       * 图片临时存放目录
       */
      private String userName;         //FTP 登录用户名
      private String password;         //FTP 登录密码
      private String ip;                     //FTP 服务器地址IP地址
      private  int port;                        //FTP 端口
      private String rootPath;
      //private  ChannelSftp sftp = null; //FTP 客户端代理
      private File tempFile;//临时文件夹
      //FTP状态码
      public static int i = 1;
      
      private void init() {
    	  
      }
      
     /* public SFtpTool getTool(){
    	  ClassPathResource cr = new ClassPathResource("application.attribute");
    	  Properties pros = new Properties();
    	  SFtpTool tool=null; 
    	  try {
    		 // image.access.path=http://192.168.4.54/root
    			  
    		  pros.load(cr.getInputStream());
    		  String ip=pros.getProperty("image.server.ip");
    		  int port=Integer.parseInt(pros.getProperty("image.server.port"));
    		  String root=pros.getProperty("image.server.rootPath");
    		  String tempPath=pros.getProperty("image.server.tempPath");
    		  String user=pros.getProperty("image.server.name");
    		  String pwd=pros.getProperty("image.server.pwd");
    		  tool =new SFtpTool(ip, port, user, pwd, root);  
    	  } catch (IOException ex) {
    		  logger.error("Do not found attribute");
    	  }
    	  
    	  return tool;
      }*/
      public SFtpTool(){
    	  super();
      }
      public SFtpTool(String ip, int port, String user, String pwd, String root, String localPath){
    	  this.userName=user;
    	  this.ip=ip;
    	  this.port=port;
    	  this.password=pwd;
    	  this.rootPath=root;
    	  tempFile=new File(localPath);
      }
      
      public static void main(String[] args) {
  		try {
  			String host = "61.144.192.95";
  			int port = 22;
  			String username = "dongzuo";
  			String password = "rfvbnju!@#456";
  			String directory = "/home/dongzuo/tomcat7/webapps/images";
  			String uploadFile = "E:/img";
  			SFtpTool sftp=new SFtpTool(host, port, username, password, directory,uploadFile);
  			sftp.connectServer();
  			sftp.uploadManyFile(uploadFile);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		
  	}
    
    public String upload(String filePath){
		try {
			File tpFile=new File(filePath);
			logger.info("start uploading "+tpFile.getName());
			//this.uploadManyFile(filePath);
			String access=this.uploadFile(tpFile,this.rootPath);
			logger.info("upload componented...");
			return access;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("SFTP UPLOAD ERROR", e);
		}
		return null;
	}
		
      
	/**
	 * 连接sftp服务器
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public  void connectServer()throws Exception {
//		if(this.sftp == null){
//			try {
//				logger.info("sftp connecting...");
//				JSch jsch = new JSch();
//				jsch.getSession(userName, ip, port);
//				Session sshSession = jsch.getSession(userName, ip, port);
//				sshSession.setPassword(password);
//				Properties sshConfig = new Properties();
//				sshConfig.put("StrictHostKeyChecking", "no");
//				sshSession.setConfig(sshConfig);
//				sshSession.connect();
//				Channel channel = sshSession.openChannel("sftp");
//				channel.connect();
//				sftp = (ChannelSftp) channel;
//				logger.info("sftp connected...");
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("SFTP connection error", e);
//			}
//		}else{
//			if(sftp.isClosed() || !sftp.isConnected()){
//				sftp.connect();
//				return;
//			}
//		}
	}
	
	
	/**
     * 上传多个文件
     *
     * @param localFile--本地文件夹路径
     * @return true 上传成功，false 上传失败
     */
    private String uploadManyFile(String localFile) {
           String accessPath=null;
            StringBuffer strBuf = new StringBuffer();
            try {
                    File file = new File(localFile);        // 在此目录中找文件
                    File fileList[] = file.listFiles();
                    for (File f : fileList) {
                            if (f.isDirectory()) {            // 文件夹中还有文件夹

                                    uploadManyFile(f.getAbsolutePath());
                            } else {
                            	accessPath = uploadFile(f, rootPath); 
                            }
                            if (!LogicUtils.isNotNullAndEmpty(accessPath)) {
                                    strBuf.append(f.getName() + "\r\n");
                            }
                    }
            } catch (NullPointerException e) {
            		e.printStackTrace();
                    logger.debug("本地文件上传失败！", e);
            } catch (Exception e) {
            		e.printStackTrace();
                    logger.debug("本地文件上传失败！", e);
            }
            return accessPath;
    }

    /**
     * 上传单个文件，并重命名
     *
     * @param localFile--本地文件路径
     * @param distFolder--新的文件名,可以命名为空""
     * @return true 上传成功，false 上传失败
     */
    public String uploadFile(File localFile,  final String distFolder) {
    	 	String accessPath="";
//            try {
////                    InputStream input = new FileInputStream(localFile);
////                    String furi1 = localFile.getParentFile().getAbsoluteFile().toURI().toString();
////                    String furi2 = tempFile.getAbsoluteFile().toURI().toString();
////                    String objFolder = distFolder +"/"+ furi1.substring(furi2.length());
////                    sftp.cd("/");
////                    if(objFolder.endsWith("/")||objFolder.endsWith("\\")){
////                      	objFolder=objFolder.substring(0,objFolder.length()-1);
////                      }
////                    try{
////            		    sftp.cd(objFolder);
////	            	}catch(SftpException sException){
////	            		    if(ChannelSftp.SSH_FX_NO_SUCH_FILE == sException.id){
////	            		    	 mkDirs(objFolder);
////	            		    }
////	            	}
////                    sftp.cd(objFolder);
////                	sftp.put(input, localFile.getName());
////                    input.close();
////                    accessPath=furi1.substring(furi2.length())+localFile.getName();
//            } catch (IOException e) {
//            	e.printStackTrace();
//                logger.error("本地文件上传失败！", e);
//            } catch (Exception e) {
//            	e.printStackTrace();
//                logger.error("本地文件上传失败！", e);
//            }
            return accessPath;
    }

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
//	public static void upload(String directory, String uploadFile, ChannelSftp sftp) {
//		try {
//			sftp.cd(directory);
//			File file = new File(uploadFile);
//			sftp.put(new FileInputStream(file), file.getName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
//	public void download(String directory, String downloadFile,
//			String saveFile, ChannelSftp sftp) {
//		try {
//			sftp.cd(directory);
//			File file = new File(saveFile);
//			sftp.get(downloadFile, new FileOutputStream(file));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 */
//	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
//		try {
//			sftp.cd(directory);
//			sftp.rm(deleteFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
//	public Vector listFiles(String directory, ChannelSftp sftp)
//			throws SftpException {
//		return sftp.ls(directory);
//	}
	
	  /**
     * 创建指定文件夹
     *
     * @param dirName
     *            dirName
     */
    private void mkDirs(String dirName)
    {
        String[] dirs = dirName.split("/");
//        try
//        {
//            String now = sftp.pwd();
//            for (int i = 0; i < dirs.length; i++)
//            {
//            	if(LogicUtils.isNotNullAndEmpty(dirs[i])){
//            		 boolean dirExists = openDir(dirs[i]);
//                     if (!dirExists)
//                     {
//                         sftp.mkdir(dirs[i]);
//                         sftp.cd(dirs[i]);
//                     }
//            	}
//
//            }
//            sftp.cd(now);
//        }
//        catch (SftpException e)
//        {
//            logger.error("mkDir Exception : " + e);
//        }
    } 
	
    
    /**
     * 打开指定目录
     *
     * @param directory
     *            directory
     * @return 是否打开目录
     */
    private boolean openDir(String directory)
    {
//        try
//        {
//            sftp.cd(directory);
//            return true;
//        }
//        catch (SftpException e)
//        {
//            logger.error("openDir Exception : " + e);
//            return false;
//        }
		return false;
    } 
    
	/**
	 * 退出SFTP
	 */
//	public  void exits(){
//		sftp.exit();
//		logger.info("exits ...");
//	}
//	public  void disconnect(){
//		sftp.disconnect();
//		logger.info("distory connection ...");
//	}
}