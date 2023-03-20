import java.io.IOException;

import controllerOGK.ModeratorOGK;
import viewsOGK.ModeratorGuiOGK;

public class MainOGK {

    public static void main(String[] args) throws IOException {
        new ModeratorGuiOGK(new ModeratorOGK());
    }

}