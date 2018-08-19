package com.neo.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class FastDFSClient {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

	static {
		try {
			String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();;
			ClientGlobal.init(filePath);
		} catch (Exception e) {
			logger.error("FastDFS Client Init Fail!",e);
		}
	}

	public static String[] upload(FastDFSFile file) {
		logger.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

		NameValuePair[] meta_list = new NameValuePair[1];
		meta_list[0] = new NameValuePair("author", file.getAuthor());

		long startTime = System.currentTimeMillis();
		String[] uploadResults = null;
		StorageClient storageClient=null;
		try {
			storageClient = getTrackerClient();
			uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
		} catch (IOException e) {
			logger.error("IO Exception when uploadind the file:" + file.getName(), e);
		} catch (Exception e) {
			logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
		}
		logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

		if (uploadResults == null && storageClient!=null) {
			logger.error("upload file fail, error code:" + storageClient.getErrorCode());
		}
		String groupName = uploadResults[0];
		String remoteFileName = uploadResults[1];

		logger.info("upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
		return uploadResults;
	}

	public static FileInfo getFile(String groupName, String remoteFileName) {
		try {
			StorageClient storageClient = getTrackerClient();
			return storageClient.get_file_info(groupName, remoteFileName);
		} catch (IOException e) {
			logger.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
		}
		return null;
	}

	public static InputStream downFile(String groupName, String remoteFileName) {
		try {
			StorageClient storageClient = getTrackerClient();
			byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
			InputStream ins = new ByteArrayInputStream(fileByte);
			return ins;
		} catch (IOException e) {
			logger.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
		}
		return null;
	}

	public static void deleteFile(String groupName, String remoteFileName)
			throws Exception {
		StorageClient storageClient = getTrackerClient();
		int i = storageClient.delete_file(groupName, remoteFileName);
		logger.info("delete file successfully!!!" + i);
	}

	public static StorageServer[] getStoreStorages(String groupName)
			throws IOException {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		return trackerClient.getStoreStorages(trackerServer, groupName);
	}

	public static ServerInfo[] getFetchStorages(String groupName,
												String remoteFileName) throws IOException {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
	}

	public static String getTrackerUrl() throws IOException {
		return "http://"+getTrackerServer().getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port()+"/";
	}

	private static StorageClient getTrackerClient() throws IOException {
		TrackerServer trackerServer = getTrackerServer();
		StorageClient storageClient = new StorageClient(trackerServer, null);
		return  storageClient;
	}

	private static TrackerServer getTrackerServer() throws IOException {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		return  trackerServer;
	}
}