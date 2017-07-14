package com;
import com.jcraft.jsch.*;
import java.io.*;;
public class WriteFileToServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String SFTPHOST = "192.168.1.10";
        int SFTPPORT = 22;
        String SFTPUSER = "manoj";
        String SFTPPASS = "manoj";
        String SFTPWORKINGDIR = "/";

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        System.out.println("preparing the host information for sftp.");
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            System.out.println("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            //File f = new File(fileName);
            //channelSftp.put("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\SHA2 Certificate Upgrade.pdf",SFTPWORKINGDIR );
            System.out.println("File transfered successfully to host.");
        } catch (Exception ex) {
             System.out.println("Exception found while tranfer the response."+ex.getMessage());
        }
        finally{

            channelSftp.exit();
            System.out.println("sftp Channel exited.");
            channel.disconnect();
            System.out.println("Channel disconnected.");
            session.disconnect();
            System.out.println("Host Session disconnected.");
        }
		
		

	}

}
