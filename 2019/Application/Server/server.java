package test;

import java.io.*;
import java.net.*;

public class server {
	
	public static void main(String[] args) throws IOException {
		System.out.println("������ ���۵Ǿ����ϴ�.");

		while(true) {
			//ServerSocket ����
			ServerSocket serverSocket = new ServerSocket(3000);
			System.out.println("\n");

			//ClientSocket ���� ���
			Socket socket = serverSocket.accept(); 
			// System.out.println("�����غ� �Ϸ�");

			//Ŭ���̾�Ʈ�� ���� �޼��� �ޱ�.
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			String fileNameStr = dis.readUTF(); // Ŭ���̾�Ʈ �޼���
		       

			//Ŭ���̾�Ʈ�� �޼��� ������.
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			switch(fileNameStr) {
			// TEXT PAGE - �ؽ�Ʈ
			/*
			case "Splash":
				System.out.println("Ŭ���̾�Ʈ�� ���� ���� ��� : Splash");
								
				String StrPath = "C:\\SendDataExample\\VideoFolder\\" ;
				String FileListTxt = "FileList.txt";
				
				File DirVideo = new File(StrPath);
				
				
				String StrVideoList[] = DirVideo.list(new FilenameFilter() { //���� ���� ����
					@Override
					public boolean accept(File dir, String name) {
						return name.startsWith("2019.12.");
					}
				});
				
				// ���� ���� Ȯ��.
				//System.out.println("����� ���� ���� = " + StrVideoList.length) ;
				try {
					if (StrVideoList.length > 8 ) { //���� ������ 8�� �̻��϶�,
						System.out.println("���� PC�� ����� ���� ���� ������ 8���� �ʰ��߽��ϴ�.");
						
						while(DirVideo.exists()) {
							File[] FileVideoList = DirVideo.listFiles(); //���ϸ���Ʈ ������
							
							for (int j = 0; j < StrVideoList.length - 8 ; j++) {
								FileVideoList[j].delete(); //���� ���� 
								System.out.println( FileVideoList[j] + " ������ �����Ǿ����ϴ�.");
							}
							break;
						}
					}
				}
				catch (Exception e) {
					e.getStackTrace();
				}
				
				
				
				
				*/
				
				
				
			case "TextData":
				System.out.println("Ŭ���̾�Ʈ�� ���� ���� ��� : TextPage");
				
				String TextFileName = "data_test.txt";
				FileInputStream fin_txt = new FileInputStream("C:\\SendDataExample\\TextFolder\\"+TextFileName);
				
				while(true){ 
					int data=fin_txt.read();
					if(data == -1) break;
					dos.write(data);
				}
				
				fin_txt.close();
				dos.close();
				dis.close();
				socket.close();
				serverSocket.close();
				
				break;
				
			case "sixdata":
				System.out.println("Ŭ���̾�Ʈ�� ���� ���� ��� : sixdata");
				
				String dataFileName = "Data.txt";
				FileInputStream filetxt = new FileInputStream("C:\\SendDataExample\\TextFolder\\"+dataFileName);
				
				while(true) {
					int data = filetxt.read();
					if(data == -1) break;
					dos.write(data);
				}
				
				filetxt.close();
				dos.close();
				dis.close();
				socket.close();
				serverSocket.close();
				
				break;
				
			/*	
			case "TextPage2" :
				System.out.println("Ŭ���̾�Ʈ�� ���� ���� ��� : TextPage2");
				
				String TextFileName1 = "data_test.txt";
				FileInputStream fin_txt1 = new FileInputStream("C:\\SendDataExample\\TextFolder\\"+TextFileName1);
				
				while(true) {
					int data = fin_txt1.read();
					if(data == -1) break;
					dos.write(data);
				}
				
				fin_txt1.close();
				dos.close();
				dis.close();
				socket.close();
				serverSocket.close();
				
				break;				
				*/
				
			// VIDEO PAGE - ������
				/*
			case "VideoPage":
				System.out.println("Ŭ���̾�Ʈ�� ���� ���� ��� : VideoPage");
				
				
				String StrPath0 = "C:\\SendDataExample\\VideoFolder\\" ;
				String FileListTxt0 = "FileList.txt";
				
				File DirVideo0 = new File(StrPath0);
				
				
				String StrVideoList0[] = DirVideo0.list(new FilenameFilter() { //���� ���� ����
					@Override
					public boolean accept(File dir, String name) {
						return name.startsWith("2019.12.");
					}
				});
				
				// ���� ���� Ȯ��.
				//System.out.println("����� ���� ���� = " + StrVideoList0.length) ;
				
				
				
				
				
				try {
					File file = new File( "C:\\SendDataExample\\VideoFolder\\" + FileListTxt0 );
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
					
					if(file.isFile() && file.canWrite()){
						if(StrVideoList0.length > 0){
							for(int i=0; i < StrVideoList0.length; i++){
								bufferedWriter.write(StrVideoList0[i] + "\n");
							}
							//System.out.println(" ���� ����Ʈ ���� �Ϸ�. ") ; // �ܼ� ��� 
						}
						bufferedWriter.close();
					}
				}
				catch (IOException e) {
					System.out.println(e);
				}
					
					
					
				// ���� ����Ʈ �ؽ�Ʈ ���� ����.
				String VideoFileList = "FileList.txt";
				FileInputStream finlst_mp4 = new FileInputStream("C:\\SendDataExample\\VideoFolder\\"+ VideoFileList);
					
				while(true){ 
					int data = finlst_mp4.read();
					if(data == -1) break;
					dos.write(data);
				}
					
				
				
				
				
			
				finlst_mp4.close();
				dos.close();
				dis.close();
				socket.close();
				serverSocket.close();
				break;
				
				
				
				
				
				
			// Video List Name Take 
			 */
			default: 
				try {
					/*
					FileInputStream fin_mp4 = new FileInputStream("C:\\SendDataExample\\VideoFolder\\"+fileNameStr);
					
					System.out.println("Ŭ���̾�Ʈ�� ���� ���� ��� : " + fileNameStr);
					while(true){ 
						int data=fin_mp4.read();
						if(data == -1) break;
						dos.write(data);
					}
					System.out.println(fileNameStr + "�ٿ�ε� �Ϸ�. ");*/
					
					
					//fin_mp4.close();
					
					System.out.println("Ŭ���̾�Ʈ�� ���� ���� �޽��� : " + fileNameStr);
					
					dos.close();
					dis.close();
					socket.close();
					serverSocket.close();
					
					break;
				
				}catch(ArithmeticException error) {
					System.out.println("�� ��𼱰� ���� �߻� (1).");
					dos.close();
					dis.close();
					socket.close();
					serverSocket.close();
					break;
					
				}catch (Exception error) {
					System.out.println("Ŭ���̾�Ʈ�� ���� ���� �޼��� : " + fileNameStr +"\n");
	                
	                dos.close();
					dis.close();
					socket.close();
					serverSocket.close();
					//e.printStackTrace();
					break;
					
					
					
	            
	            }

				
				
			}
		}
	}
}



	    
/*	    
	    if(fileNameStr.equals("exit")){
	    	System.out.println("���α׷� �ߴ� ��û");
	    	break;
	    }
	
	    //�������α׷��� ����Ǵ� ��ǻ�Ϳ� ���������� ����� ���� ����.
	    FileInputStream fin = new FileInputStream("C:\\SendDataExample\\"+fileNameStr);
	
	    //FileInputStream�� ���� ������ �о�鿩�� ������ ��½�Ʈ���� ���� ���.
		while(true){ 
			int data=fin.read();
			if(data == -1) break;
			dos.write(data);
		}
		
		// pw.println("From Server >" + fileNameStr);   
		
	    //��Ʈ�� , ���� �ݱ�
		fin.close();
		dos.close();
		dis.close();
		socket.close();
		serverSocket.close();
	  }

	}
}
*/