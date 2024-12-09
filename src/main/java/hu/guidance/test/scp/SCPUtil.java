package hu.guidance.test.scp;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

// https://stackoverflow.com/a/57133202/600007
public class SCPUtil {

	private Session session;
	private ChannelSftp channel;


	public synchronized void connect(String host, int port, String username, String password) throws JSchException {
		JSch jsch = new JSch();

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");

		session = jsch.getSession(username, host, port);
		session.setPassword(password);
		session.setConfig(config);
		session.setInputStream(System.in);
		session.connect();

		channel = (ChannelSftp) session.openChannel("sftp");
		channel.connect();
	}

	public synchronized void uploadFile(String directoryPath, String fileName, byte[] fileBytes, boolean overwrite) throws SftpException {
		// a workaround to check if the directory exists. Otherwise, create it
		channel.cd("/");
		String[] directories = directoryPath.split("/");
		for(String directory : directories){
			if(directory.length() > 0) {
				try {
					channel.cd(directory);
				} catch(SftpException e) {
					// swallowed exception
					System.out.println("The directory (" + directory + ") seems to be not exist. We will try to create it.");

					try {
						channel.mkdir(directory);
						channel.cd(directory);
						System.out.println("The directory (" + directory + ") is created successfully!");
					} catch(SftpException e1) {
						System.err.println("The directory (" + directory + ") is failed to be created!");
						e1.printStackTrace();
						return;
					}

				}
			}
		}
		
		channel.put(new ByteArrayInputStream(fileBytes), directoryPath + "/" + fileName, overwrite ? ChannelSftp.OVERWRITE : ChannelSftp.RESUME);
	}

	public synchronized void disconnect() {
		channel.exit();
		channel.disconnect();
		session.disconnect();
	
		channel = null;
		session = null;
	}	
	
}
