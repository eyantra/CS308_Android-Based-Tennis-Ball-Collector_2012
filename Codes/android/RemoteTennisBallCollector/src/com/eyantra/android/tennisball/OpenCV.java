package com.eyantra.android.tennisball;

public class OpenCV {
	static{
		System.loadLibrary("opencv");
	}
	public native boolean setSourceImage(int[] pixels, int width, int height);
	public native byte[] getSourceImage();
	public native void extractSURFFeature();
	public native int[] locateBall();
}