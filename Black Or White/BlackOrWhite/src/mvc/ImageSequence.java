package mvc;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JFrame;

public class ImageSequence { //����e������
	private String baseFileName; //�Ϥ��ɥD�W
	private String fileExt; //�Ϥ��ɪ��[�W(�ppng��)
	private int numFiles; //����e���ɭӼ�
	private Image[] imgs ; //�x�s����e������(Image����}�C)
	private int index = -1; //����e������
	public ImageSequence(String bfname, String fext, int n) { //�غc�l
		imgs = new Image[n];
		numFiles = n;
		baseFileName = bfname;
		fileExt = fext;
	 try {
		 for (int i=0; i<numFiles; i++)
			 imgs[i] = ImageIO.read(new File(bfname+i+"."+fext)); //load the pic files
	 } catch (Exception e) {e.printStackTrace();}
	}
	public Image next(boolean cycle) { //���o�U�@�i�e��
		 if (!cycle && index == numFiles-1) { //cycle�Ofalse��ܤ����Ƽ���
			 index = -1;
			 return null;
		 }
		 index = (index+1) % numFiles; //�p��U�@�e������
		 return imgs[index];
	}
	public void reset () { index = -1;} //���]���_�l���A
	
	

}

	 
