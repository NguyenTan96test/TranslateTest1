package Manager;

import Singleton.Singleton;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class SoundManager extends Singleton<SoundManager> {
    private String wordToSpeak;

    public String getWordToSpeak() {
        return wordToSpeak;
    }

    public void setWordToSpeak(String wordToSpeak) {
        this.wordToSpeak = wordToSpeak;
    }

    public void speakWord() {
        try
        {
//setting properties as Kevin Dictionary
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
//registering speech engine
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
//create a Synthesizer that generates voice
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
//allocates a synthesizer
            synthesizer.allocate();
//resume a Synthesizer
            synthesizer.resume();
//speak the specified text until the QUEUE become empty
            synthesizer.speakPlainText(wordToSpeak, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
//deallocating the Synthesizer
            synthesizer.deallocate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
