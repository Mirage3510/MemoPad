package io_text;

import com.sun.jna.Library; 
import com.sun.jna.Native; 
import com.sun.jna.NativeMapped; 
import com.sun.jna.Platform; 
import com.sun.jna.PointerType; 


public class Pass {
	
	 public static class HANDLE extends PointerType implements NativeMapped {} 
	 public static class HWND extends HANDLE {} 
	  
	 public interface Shell32 extends Library { 
	  Shell32 INSTANCE = (Shell32) Native.loadLibrary("shell32", Shell32.class); 
	   
	  public static final int MAX_PATH = 260; 
	  public static final int CSIDL_PERSONAL = 0x0005; // マイドキュメント 
	  public static final int SHGFP_TYPE_CURRENT = 0; 
	  public static final int SHGFP_TYPE_DEFAULT = 1; 
	  public static final int S_OK = 0; 
	   
	  int SHGetFolderPathW( 
	    HWND hwndOwner, 
	    int nFolder, 
	    HANDLE hToken, 
	    int dwFlags, 
	    char[] lpdzShortPath); 
	 } 
	  
	 public String SHGetFolderPath() { 
	  if (Platform.isWindows()) { 
	   HWND hwndOwner = null; 
	   int nFolder = Shell32.CSIDL_PERSONAL; 
	   HANDLE hToken = null; 
	   int dwFlags = Shell32.SHGFP_TYPE_CURRENT; 
	   char pszPath [] = new char [2+256*2]; 
	   int res = Shell32.INSTANCE.SHGetFolderPathW(hwndOwner, nFolder, hToken, dwFlags, pszPath); 
	    
	   if (res == Shell32.S_OK) { 
	    String path = new String(pszPath); 
	    path = path.substring(0, path.indexOf('\0')); 
	    return path; 
	   } 
	  } 
	  return null; 
	 } 
}
