package halamish.reem.referandum;

/**
 * Created by Re'em on 5/20/2016.
 */
public class PollManager {
    private static PollManager instance;

    private Poll currentPoll;
    private boolean isActiveCurrentPoll;
    private PollManager(){}
    public static synchronized PollManager getManager() {
        if (instance == null)
            instance = new PollManager();
        return instance;
    }


    public Poll getCurrentPoll() {
        return currentPoll;
    }

    public void setCurrentPoll(Poll currentPoll) {
        this.currentPoll = currentPoll;
    }

    public boolean isActiveCurrentPoll() {
        return isActiveCurrentPoll;
    }

    public void setIsActiveCurrentPoll(boolean isActiveCurrentPoll) {
        this.isActiveCurrentPoll = isActiveCurrentPoll;
    }
}
