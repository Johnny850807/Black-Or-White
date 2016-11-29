package mvc;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JFrame;

public class ImageSequence { //分鏡畫面物件
	private String baseFileName; //圖片檔主名
	private String fileExt; //圖片檔附加名(如png檔)
	private int numFiles; //分鏡畫面檔個數
	private Image[] imgs ; //儲存分鏡畫面圖檔(Image物件陣列)
	private int index = -1; //分鏡畫面索引
	public ImageSequence(String bfname, String fext, int n) { //建構子
		imgs = new Image[n];
		numFiles = n;
		baseFileName = bfname;
		fileExt = fext;
	 try {
		 for (int i=0; i<numFiles; i++)
			 imgs[i] = ImageIO.read(new File(bfname+i+"."+fext)); //load the pic files
	 } catch (Exception e) {e.printStackTrace();}
	}
	public Image next(boolean cycle) { //取得下一張畫面
		 if (!cycle && index == numFiles-1) { //cycle是false表示不重複播放
			 index = -1;
			 return null;
		 }
		 index = (index+1) % numFiles; //計算下一畫面索引
		 return imgs[index];
	}
	public void reset () { index = -1;} //重設為起始狀態
	
	

}

	 
