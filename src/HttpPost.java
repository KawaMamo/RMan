import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpPost {
    private final String crlf = "\r\n";
    private URL url;
    private URLConnection urlConnection;
    private OutputStream outputStream;
    private InputStream inputStream;
    private String[] fileNames;
    private String output;
    private String boundary;
    private final int bufferSize = 4096;

    private String newName;

    public void setNewName(String newName){
        this.newName = newName;
    }

    public HttpPost(URL argUrl) {
        url = argUrl;
        boundary = "---------------------------4664151417711";
    }

    public void setFileNames(String[] argFiles) {
        fileNames = argFiles;
    }

    public void post() {
        try {
            urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            String fileName = fileNames[0];
            InputStream fileInputStream = new FileInputStream(fileName);

            byte[] fileData = new byte[fileInputStream.available()];
            fileInputStream.read(fileData);

            // ::::: PART 1 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            String part1 = "";
            part1 += "--" + boundary + crlf;
            File f = new File(fileNames[0]);
            fileName = f.getName(); // we do not want the whole path, just the name
            part1 += "Content-Disposition: form-data; name=\"userfile\"; filename=\"" + newName + "\""
                    + crlf;

            // CONTENT-TYPE
            // TODO: add proper MIME support here
            if (fileName.endsWith("png")) {
                part1 += "Content-Type: image/png" + crlf;
            } else {
                part1 += "Content-Type: image/jpeg" + crlf;
            }

            part1 += crlf;
            // File's binary data will be sent after this part

            // ::::: PART 2 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            String part2 = crlf + "--" + boundary + "--" + crlf;



            urlConnection.setRequestProperty("Content-Length",
                    String.valueOf(part1.length() + part2.length() + fileData.length));


            // ::::: File send ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            outputStream = urlConnection.getOutputStream();
            outputStream.write(part1.getBytes());

            int index = 0;
            int size = bufferSize;
            do {
                if ((index + size) > fileData.length) {
                    size = fileData.length - index;
                }
                outputStream.write(fileData, index, size);
                index += size;
            } while (index < fileData.length);

            outputStream.write(part2.getBytes());
            outputStream.flush();

            // ::::: Download result into the 'output' String :::::::::::::::::::::::::::::::::::::::::::::::
            inputStream = urlConnection.getInputStream();
            StringBuilder sb = new StringBuilder();
            char buff = 512;
            int len;
            byte[] data = new byte[buff];
            do {

                len = inputStream.read(data);

                if (len > 0) {
                    sb.append(new String(data, 0, len));
                }
            } while (len > 0);
            output = sb.toString();
            //output = "Done";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                inputStream.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    } // post() method

    public String getOutput() {
        return output;
    }
}
