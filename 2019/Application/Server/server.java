package test;

import java.io.*;
import java.net.*;

public class server {
	
	public static void main(String[] args) throws IOException {
		System.out.println("서버가 시작되었습니다.");

		while(true) {
			//ServerSocket 생성
			ServerSocket serverSocket = new ServerSocket(3000);
			System.out.println("\n");

			//ClientSocket 응답 대기
			Socket socket = serverSocket.accept(); 
			// System.out.println("소켓준비 완료");

			//클라이언트로 부터 메세지 받기.
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			String fileNameStr = dis.readUTF(); // 클라이언트 메세지
		       

			//클라이언트에 메세지 보내기.
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			switch(fileNameStr) {
			// TEXT PAGE - 텍스트
			/*
			case "Splash":
				System.out.println("클라이언트로 부터 받은 명령 : Splash");
								
				String StrPath = "C:\\SendDataExample\\VideoFolder\\" ;
				String FileListTxt = "FileList.txt";
				
				File DirVideo = new File(StrPath);
				
				
				String StrVideoList[] = DirVideo.list(new FilenameFilter() { //접두 문자 지정
					@Override
					public boolean accept(File dir, String name) {
						return name.startsWith("2019.12.");
					}
				});
				
				// 파일 개수 확인.
				//System.out.println("저장된 파일 개수 = " + StrVideoList.length) ;
				try {
					if (StrVideoList.length > 8 ) { //파일 개수가 8개 이상일때,
						System.out.println("서버 PC에 저장된 비디오 파일 개수가 8개를 초과했습니다.");
						
						while(DirVideo.exists()) {
							File[] FileVideoList = DirVideo.listFiles(); //파일리스트 얻어오기
							
							for (int j = 0; j < StrVideoList.length - 8 ; j++) {
								FileVideoList[j].delete(); //파일 삭제 
								System.out.println( FileVideoList[j] + " 파일이 삭제되었습니다.");
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
				System.out.println("클라이언트로 부터 받은 명령 : TextPage");
				
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
				System.out.println("클라이언트로 부터 받은 명령 : sixdata");
				
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
				System.out.println("클라이언트로 부터 받은 명령 : TextPage2");
				
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
				
			// VIDEO PAGE - 동영상
				/*
			case "VideoPage":
				System.out.println("클라이언트로 부터 받은 명령 : VideoPage");
				
				
				String StrPath0 = "C:\\SendDataExample\\VideoFolder\\" ;
				String FileListTxt0 = "FileList.txt";
				
				File DirVideo0 = new File(StrPath0);
				
				
				String StrVideoList0[] = DirVideo0.list(new FilenameFilter() { //접두 문자 지정
					@Override
					public boolean accept(File dir, String name) {
						return name.startsWith("2019.12.");
					}
				});
				
				// 파일 개수 확인.
				//System.out.println("저장된 파일 개수 = " + StrVideoList0.length) ;
				
				
				
				
				
				try {
					File file = new File( "C:\\SendDataExample\\VideoFolder\\" + FileListTxt0 );
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
					
					if(file.isFile() && file.canWrite()){
						if(StrVideoList0.length > 0){
							for(int i=0; i < StrVideoList0.length; i++){
								bufferedWriter.write(StrVideoList0[i] + "\n");
							}
							//System.out.println(" 폴더 리스트 생성 완료. ") ; // 콘솔 출력 
						}
						bufferedWriter.close();
					}
				}
				catch (IOException e) {
					System.out.println(e);
				}
					
					
					
				// 파일 리스트 텍스트 파일 전송.
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
					
					System.out.println("클라이언트로 부터 받은 명령 : " + fileNameStr);
					while(true){ 
						int data=fin_mp4.read();
						if(data == -1) break;
						dos.write(data);
					}
					System.out.println(fileNameStr + "다운로드 완료. ");*/
					
					
					//fin_mp4.close();
					
					System.out.println("클라이언트로 부터 받은 메시지 : " + fileNameStr);
					
					dos.close();
					dis.close();
					socket.close();
					serverSocket.close();
					
					break;
				
				}catch(ArithmeticException error) {
					System.out.println("※ 어디선가 오류 발생 (1).");
					dos.close();
					dis.close();
					socket.close();
					serverSocket.close();
					break;
					
				}catch (Exception error) {
					System.out.println("클라이언트로 부터 받은 메세지 : " + fileNameStr +"\n");
	                
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
	    	System.out.println("프로그램 중단 요청");
	    	break;
	    }
	
	    //서버프로그램이 실행되는 컴퓨터에 파일폴더로 사용할 폴더 생성.
	    FileInputStream fin = new FileInputStream("C:\\SendDataExample\\"+fileNameStr);
	
	    //FileInputStream을 통해 파일을 읽어들여서 소켓의 출력스트림을 통해 출력.
		while(true){ 
			int data=fin.read();
			if(data == -1) break;
			dos.write(data);
		}
		
		// pw.println("From Server >" + fileNameStr);   
		
	    //스트림 , 소켓 닫기
		fin.close();
		dos.close();
		dis.close();
		socket.close();
		serverSocket.close();
	  }

	}
}
*/