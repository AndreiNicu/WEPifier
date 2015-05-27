package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileManagment {

	private String path;

        public FileManagment()
        {
            path="";
        }

        public void setPath(String p){path=p;}

        public String getPath(){return path;}
        /**
         * This method is no longer used by the application.
         * Will be removed in later versions.
         * @deprecated 
         * @param log  Data generated for writing.
         */
        public void writeToFile(String log)
        {
            FileWriter fw = null;
            try{
                fw = new FileWriter("passwd.txt");
            } catch(IOException e)
            {
                e.printStackTrace();
            }
            if(fw==null) return;
            BufferedWriter out = new BufferedWriter(fw);            
        }
        /**
         * Method designed to search and destroy all key files, to avoid static reading.
         * @param f Local path for searching keys
         */
        public void eraseKeyFile(File f)
        {
            File[] erase = f.listFiles();
            for(File t: erase)
            {
                String str = t.getAbsolutePath();
                if(t.isFile()&&(str.endsWith("xor")))
                    t.delete();
            }
        }
        /**
         * Method will search and destroy all generated files to avoid static reading.
         * @param t Local path for searching static files
         */
        public void eraseFiles(File t)
        {
            File[] erase = t.listFiles();
            for(File f:erase)
            {
                String str = f.getAbsolutePath();
                if(f.isFile()&&(str.endsWith(".csv")||str.endsWith(".cap")||str.endsWith("netxml")))
                    f.delete();
            }
        }
        /**
         * Will search generated key file and return it for merging.
         * @param t Local path for searching
         * @return Path and name of file for further processing
         */
        public String getPacketForMerge(File t)
        {
            String file="";
            File[] gather = t.listFiles();
            for(File f: gather)
            {
                String str = f.getAbsolutePath();
                if(f.isFile()&&str.endsWith(".xor"))
                    file = str;
            }
            return file;
        }

}
