import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer 
{
	private static final int EXTERNAL_BUFFER_SIZE = 128000;
        public static final int ONCE = 1, LOOP = 2;
	
        public void playMultiple(String fName, int nLoopCount)
        {
            Clip clip = null;
            File clipFile = new File(fName);
            AudioInputStream audioInputStream = null;
            try
            {
                audioInputStream = AudioSystem.getAudioInputStream(clipFile);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if (audioInputStream != null)
            {
                AudioFormat	format = audioInputStream.getFormat();
                DataLine.Info	info = new DataLine.Info(Clip.class, format);
                try
                {
                        clip = (Clip) AudioSystem.getLine(info);
                        clip.open(audioInputStream);
                }
                catch (LineUnavailableException e)
                {
                        e.printStackTrace();
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                }
                clip.loop(nLoopCount);
            }
            else
            {
                System.out.println("Error in reading " + clipFile.getName());
            }	
        }
      
	public void play(String fileName)
	{
            File soundFile = new File(fileName);
            AudioInputStream audioInputStream = null;
            try
            {
                    audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }

            AudioFormat	audioFormat = audioInputStream.getFormat();

            SourceDataLine line = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            try
            {
                line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(audioFormat);
            }
            catch (LineUnavailableException e)
            {
                    e.printStackTrace();
            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }

            line.start();

            int	nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
            while (nBytesRead != -1)
            {
                try
                {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (nBytesRead >= 0)
                {
                    int	nBytesWritten = line.write(abData, 0, nBytesRead);
                }
            }
            line.close();
	}
}
