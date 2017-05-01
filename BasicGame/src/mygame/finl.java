package mygame;

import java.util.*;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import home.playclient;
import home.stgl;
import home.user;
import home.userhome;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.swing.*;
import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import home.thome;
import home.tplay;
import home.twin;
import jssc.SerialPort;
import jssc.SerialPortException;

public class finl extends SimpleApplication {

    public static user us1, us2, us3, us4, us5, us6;
    animation ball;
    animation back;
    Node myrootnode;
    animation plpnt, enpnt;
    int goal = 0, engoal = 0;
    Date d;
    control cntrl = new control();
    public static control encntrl;
    static finl app;
    Material engoalmat;
    Material goalmat, minmat, min1mat, secmat, sec1mat;
    Thread thh;
    static Thread serial;

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
        //for serial
        serial = new cMain();
        serial.start();

        staticglobal.cleanup();
        app = new finl();
        AppSettings settings = new AppSettings(true);
        app.setShowSettings(false);
        settings.setFullscreen(true);
        settings.setFrameRate(24);
        settings.setResolution(1920, 1080);
        settings.setTitle("JAVABALL");
        settings.setVSync(true);
        //settings.setGammaCorrection(true);
        app.setDisplayFps(false);
        app.setDisplayStatView(false);
        app.setSettings(settings);
        app.start();
        Logger.getLogger("com.jme3").setLevel(SEVERE);
    }

    public Spatial objloader(String path) {
        Spatial sp = assetManager.loadModel(path);
        return sp;
    }

    public Spatial[] objarrayloader(int n, String folder) {
        Spatial sp[] = new Spatial[n];
        int i;
        String str = "Models/" + folder + "/1.j3o";
        for (i = 0; i < n; i++) {
            String c = "/" + Integer.toString(i);
            String c1 = "/" + Integer.toString(i + 1);
            str = str.replace(c, c1);

            sp[i] = objloader(str);

        }
        return sp;
    }

    public Spatial[] objarrayloader(int n, String folder, Material m) {
        Spatial sp[] = new Spatial[n];
        int i;
        String str = "Models/" + folder + "/1.j3o";
        for (i = 0; i < n; i++) {
            String c = "/" + Integer.toString(i);
            String c1 = "/" + Integer.toString(i + 1);
            str = str.replace(c, c1);

            sp[i] = objloader(str);
            //sp[i].setMaterial(m);

        }
        return sp;
    }

    public animation animationloader(int frames, String path) {
        Spatial objarray[] = objarrayloader(frames, path);
        animation Animation = new animation(objarray.length, objarray);
        return Animation;
    }

    public animation animationloader(int frames, String path, int size) {
        Spatial objarray[] = objarrayloader(frames, path);
        animation Animation = new animation(objarray.length, objarray, size);
        return Animation;
    }

    public animation animationloader(int frames, String path, int size, Material m) {
        Spatial objarray[] = objarrayloader(frames, path, m);
        animation Animation = new animation(objarray.length, objarray, size);
        return Animation;
    }

    public animation animationloader(String path, float size) {
        Spatial objarray[] = objarrayloader(1, path);
        animation Animation = new animation(objarray.length, objarray);
        Animation.node.scale(size);

        return Animation;
    }

    public animation animationloader(String path, int size, Material m) {
        Spatial objarray[] = objarrayloader(1, path, m);
        animation Animation = new animation(objarray.length, objarray);
        Animation.node.scale(size);
        return Animation;
    }

    public int datetosec(Date d1, Date d2) {
        int x = 0;
        x += d1.getSeconds() - d2.getSeconds();
        x += (d1.getMinutes() - d2.getMinutes()) * 60;
        return x;
    }

    @Override
    public void simpleInitApp() {
AudioNode audio_nature = new AudioNode(assetManager, "Sounds/crowd.wav", AudioData.DataType.Stream);
        audio_nature.setLooping(true);  // activate continuous playing
        audio_nature.setPositional(true);
        audio_nature.setVolume(10);
        rootNode.attachChild(audio_nature);
        audio_nature.play(); // play continuously!
        flyCam.setEnabled(false);
        myrootnode = new Node();
        myrootnode.setLocalTranslation(0.0f, 0.0f, -1.5f);

        viewPort.setBackgroundColor(ColorRGBA.LightGray);

        //*settings-end
        //loading animations-start
        back = animationloader("field", 25);
        ball = animationloader("ball", (float) 0.1);
        plpnt = animationloader("plpnt", 15);
        enpnt = animationloader("enpnt", 15);

        ball.node.setLocalTranslation(0.0f, 0.2f, 0.0f);
        staticglobal.ball = new Ball(ball);
        staticglobal.pushnode(staticglobal.ball.ballnode);
        staticglobal.pushnode(plpnt.node);
        staticglobal.pushnode(enpnt.node);

        //loading animations-end
        //loading textures
        Material wood = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        wood.setTexture("ColorMap",
                assetManager.loadTexture("Textures/wood.jpg"));
        Material steel = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        steel.setTexture("ColorMap",
                assetManager.loadTexture("Textures/steel.jpg"));
        //loading textures

        //player loader start
        int i;
        FileReader fin = null;
        Scanner sc;
        try {
            fin = new FileReader("assets/Values/Player-pos.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(finl.class.getName()).log(Level.SEVERE, null, ex);
        }
        sc = new Scanner(fin);
        for (i = 0; i < 8; i++) {

            staticglobal.pushanimation(animationloader("playerstill", 10, wood));
            staticglobal.pushanimation(animationloader(20, "playerwalk", 10, wood));
            staticglobal.pushanimation(animationloader("playerkick", 10, wood));
            staticglobal.p[i] = new player(staticglobal.animationarray[staticglobal.currentanimationid - 3], staticglobal.animationarray[staticglobal.currentanimationid - 2], staticglobal.animationarray[staticglobal.currentanimationid - 1]);

            staticglobal.p[i].playernode.rotate(0.0f, (float) Math.toRadians(90), 0.0f);
            staticglobal.pushnode(staticglobal.p[i].playernode);
            float tx, tz;
            tx = Float.parseFloat(sc.nextLine());
            tz = Float.parseFloat(sc.nextLine());
            staticglobal.initialposx[i] = tx;
            staticglobal.initialposz[i] = tz;
            staticglobal.p[i].setPosx(tx);
            staticglobal.p[i].setPosy((float) 0.05);
            staticglobal.p[i].setPosz(tz);
        }
        try {
            fin = new FileReader("assets/Values/Enemy-pos.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(finl.class.getName()).log(Level.SEVERE, null, ex);
        }
        sc = new Scanner(fin);
        for (i = 0; i < 8; i++) {

            staticglobal.pushanimation(animationloader("playerstill_2", 10, steel));
            staticglobal.pushanimation(animationloader(20, "playerwalk_2", 10, steel));
            staticglobal.pushanimation(animationloader("playerkick_2", 10, steel));
            staticglobal.p2[i] = new enemy(staticglobal.animationarray[staticglobal.currentanimationid - 3], staticglobal.animationarray[staticglobal.currentanimationid - 2], staticglobal.animationarray[staticglobal.currentanimationid - 1]);
            staticglobal.p2[i].playernode.rotate(0.0f, (float) Math.toRadians(-90), 0.0f);
            staticglobal.pushnode(staticglobal.p2[i].playernode);
            float tx, tz;
            tx = Float.parseFloat(sc.nextLine());
            tz = Float.parseFloat(sc.nextLine());
            staticglobal.initialenposx[i] = tx;
            staticglobal.initialenposz[i] = tz;
            staticglobal.p2[i].setPosx(tx);
            staticglobal.p2[i].setPosy((float) 0.05);
            staticglobal.p2[i].setPosz(tz);

        }
        //player loader end

        //attaching animations to nodes - start
        staticglobal.pushnode(back.node);

        for (i = 1; i < staticglobal.currentnodeid; i++) {
            myrootnode.attachChild(staticglobal.nodearray[i]);
        }
        myrootnode.rotate(0.36f, 0.0f, 0.0f);
        rootNode.attachChild(myrootnode);
        //attaching animations to nodes - end

        //lighting-start
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);

        Box goal = new Box(0.1f, 0.1f, 0.01f); // create cube shape
        Geometry goalg = new Geometry("Box", goal);  // create cube geometry from the shape
        goalmat = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        goalmat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/0.jpg"));
        goalg.setMaterial(goalmat);
        goalg.setLocalTranslation(-3.5f, -1.9f, 5);
        rootNode.attachChild(goalg);

        Box engoal = new Box(0.1f, 0.1f, 0.01f); // create cube shape
        Geometry engoalg = new Geometry("Box", engoal);  // create cube geometry from the shape
        engoalmat = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        engoalmat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/0.jpg"));
        engoalg.setMaterial(engoalmat);
        engoalg.setLocalTranslation(3.5f, -1.9f, 5);
        rootNode.attachChild(engoalg);

        Box min = new Box(0.1f, 0.1f, 0.01f); // create cube shape
        Geometry ming = new Geometry("Box", min);  // create cube geometry from the shape
        minmat = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        minmat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/0.jpg"));
        ming.setMaterial(minmat);
        ming.setLocalTranslation(-0.38f, -1.9f, 5);
        rootNode.attachChild(ming);

        Box min1 = new Box(0.1f, 0.1f, 0.01f); // create cube shape
        Geometry min1g = new Geometry("Box", min1);  // create cube geometry from the shape
        min1mat = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        min1mat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/0.jpg"));
        min1g.setMaterial(min1mat);
        min1g.setLocalTranslation(-0.16f, -1.9f, 5);
        rootNode.attachChild(min1g);

        Box sec = new Box(0.1f, 0.1f, 0.01f); // create cube shape
        Geometry secg = new Geometry("Box", sec);  // create cube geometry from the shape
        secmat = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        secmat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/10.jpg"));
        secg.setMaterial(secmat);
        secg.setLocalTranslation(0.36f, -1.9f, 5);
        rootNode.attachChild(secg);

        Box sec1 = new Box(0.1f, 0.1f, 0.01f); // create cube shape
        Geometry sec1g = new Geometry("Box", sec1);  // create cube geometry from the shape
        sec1mat = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sec1mat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/10.jpg"));
        sec1g.setMaterial(sec1mat);
        sec1g.setLocalTranslation(0.13f, -1.9f, 5);
        rootNode.attachChild(sec1g);

        d = new Date();
    }

    @Override
    public void simpleUpdate(float tpf) {

        plpnt.node.setLocalTranslation(staticglobal.getcurrentplayer().posx, 0.98f, staticglobal.getcurrentplayer().posz);
        enpnt.node.setLocalTranslation(staticglobal.getcurrentenemy().posx, 0.98f, staticglobal.getcurrentenemy().posz);
        int i;
        for (i = 1; i < staticglobal.currentanimationid; i++) {
            staticglobal.animationarray[i].animate();
        }
        initKeys();
        if (cMain.x == 1) {
            if (staticglobal.ball.stuck && staticglobal.ball.possesion) {
                cntrl.pass = 1;
                if (staticglobal.center == false) {
                    pass();
                } else {
                    staticglobal.getcurrentplayer().walkleft();
                    staticglobal.center = false;
                    pass();
                }
            }
        }
        if (cMain.x == 2 && staticglobal.ball.stuck && staticglobal.ball.possesion && !staticglobal.center) {
            cntrl.tackle = 1;
            kick();

        }
        if (cMain.x == 2 && staticglobal.ball.stuck && !staticglobal.ball.possesion && !staticglobal.center) {
            cntrl.tackle = 1;

            tackle();

        }
        if (cMain.x == 3 && !staticglobal.center) {
            cntrl.le = 1;
            staticglobal.getcurrentplayer().walkback();
        }
        if (cMain.x == 6 && !staticglobal.center) {
            cntrl.ri = 1;
            staticglobal.getcurrentplayer().walkfront();

        }
        if (cMain.x == 4 && !staticglobal.center) {
            cntrl.up = 1;
            staticglobal.getcurrentplayer().walkleft();
        }
        if (cMain.x == 5 && !staticglobal.center) {
            cntrl.down = 1;
            staticglobal.getcurrentplayer().walkright();
        }
        String data1 = "";
        String data2 = "";

        for (i = 0; i < 8; i++) {

            staticglobal.p[i].update();
            staticglobal.p2[i].update();
            data1 = data1 + (staticglobal.p[i].posx) + " " + (staticglobal.p[i].posz) + " ";
            data2 = data2 + (-staticglobal.p2[i].posx) + " " + (-staticglobal.p2[i].posz) + " ";

        }
        thh = new datath(data1, data2);
        thh.start();

        staticglobal.currentplayerupdate();

        staticglobal.currentenemyupdate();

        staticglobal.playerAIupdate();

        staticglobal.enemyAIupdate();

        staticglobal.ball.update();

        staticglobal.colliderupdate();

        myrootnode.setLocalTranslation(
                (float) (-staticglobal.ball.posx / 1.5), 0.0f, -1.5f);
        if (staticglobal.ball.posx > 12.3 && staticglobal.ball.posz < 1.3 && staticglobal.ball.posz
                > -1.3) {
            goal++;AudioNode audio_gun = new AudioNode(assetManager, "Sounds/goal.wav", AudioData.DataType.Buffer);
            audio_gun.setPositional(false);
            audio_gun.setLooping(false);
            audio_gun.setVolume(10);
           rootNode.attachChild(audio_gun);audio_gun.playInstance();
            new goal().start();

            center();

        }
        if (staticglobal.ball.posx < -12.3 && staticglobal.ball.posz < 1.3 && staticglobal.ball.posz
                > -1.3) {
            engoal++;AudioNode audio_gun = new AudioNode(assetManager, "Sounds/goal.wav", AudioData.DataType.Buffer);
            audio_gun.setPositional(false);
            audio_gun.setLooping(false);
            audio_gun.setVolume(10);
           rootNode.attachChild(audio_gun);audio_gun.playInstance();
            new goal().start();

            encenter();

        }
        goalmat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/" + goal + ".jpg"));
        engoalmat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/" + engoal + ".jpg"));
        Date tm = new Date();
        int time = this.datetosec(tm, d);
        sec1mat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/sec/" + ((time % 60) / 10) + ".jpg"));
        secmat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/sec/" + ((time % 60) % 10) + ".jpg"));
        minmat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/min/" + ((time / 60) / 10) + ".jpg"));
        min1mat.setTexture("ColorMap",
                assetManager.loadTexture("Textures/min/" + ((time / 60) % 10) + ".jpg"));
        if (time >= 30 && goal != engoal) {
            thh.stop();
            us5.goal += goal;
            us5.conceded += engoal;
            if (goal > engoal) {
                us5.won++;
                us6.lost++;
            } else if (goal < engoal) {
                us6.won++;
                us5.lost++;
            }
            us6.conceded += goal;
            us6.goal += engoal;
            us5.matches++;
            us6.matches++;
            app.stop();
            try {
                new home.gameclient(us3.toString());
            } catch (ClassNotFoundException ex) {

            }
            try {
                new home.gameclient(us4.toString());
            } catch (ClassNotFoundException ex) {

            }
            stgl.usermap.put(us5.username, us5);
            stgl.usermap.put(us6.username, us6);
            if (goal > engoal) {
                home.Main.frame.setContentPane(new twin(us5));
                //Thread t=new finlreturntrhd(us5);
                //t.start();
            }
            if (goal < engoal) {
                Thread t = new finlreturntrhd(us6);
                t.start();
            }

        }

    }

    void encenter() {
        int i;
        for (i = 0; i < 8; i++) {
            staticglobal.p[i].posx = staticglobal.initialposx[i];
            staticglobal.p[i].posz = staticglobal.initialposz[i];
            staticglobal.p2[i].posx = staticglobal.initialenposx[i];
            staticglobal.p2[i].posz = staticglobal.initialenposz[i];

        }
        staticglobal.ball.posx = 0;
        staticglobal.ball.posz = 0;
        staticglobal.ball.stuck = true;
        staticglobal.ball.possesion = true;
        staticglobal.center = true;
    }

    void center() {
        int i;
        for (i = 0; i < 8; i++) {
            staticglobal.p[i].posx = staticglobal.initialposx[i];
            staticglobal.p[i].posz = staticglobal.initialposz[i];
            staticglobal.p2[i].posx = staticglobal.initialenposx[i];
            staticglobal.p2[i].posz = staticglobal.initialenposz[i];

        }
        staticglobal.ball.posx = 0;
        staticglobal.ball.posz = 0;
        staticglobal.ball.stuck = true;
        staticglobal.ball.possesion = false;
        staticglobal.center = true;

    }

    void pass() {
        int passlen = 30;

        staticglobal.ball.speed = (float) 0.1;
        staticglobal.ball.stuck = false;
        staticglobal.getcurrentplayer().kick();
        if (staticglobal.getcurrentplayer().rotstate == 1) {
            staticglobal.ball.movex(passlen);
        }
        if (staticglobal.getcurrentplayer().rotstate == 2) {
            staticglobal.ball.movez(-passlen);
        }
        if (staticglobal.getcurrentplayer().rotstate == 3) {
            staticglobal.ball.movex(-passlen);
        }
        if (staticglobal.getcurrentplayer().rotstate == 4) {
            staticglobal.ball.movez(passlen);
        }
    }

    void enpass() {
        int passlen = 30;
        staticglobal.ball.speed = (float) 0.1;
        staticglobal.getcurrentenemy().kick();
        staticglobal.ball.stuck = false;
        if (staticglobal.getcurrentenemy().rotstate == 1) {
            staticglobal.ball.movex(passlen);
        }
        if (staticglobal.getcurrentenemy().rotstate == 2) {
            staticglobal.ball.movez(-passlen);
        }
        if (staticglobal.getcurrentenemy().rotstate == 3) {
            staticglobal.ball.movex(-passlen);
        }
        if (staticglobal.getcurrentenemy().rotstate == 4) {
            staticglobal.ball.movez(passlen);
        }
    }

    void enkick() {
        int passlen = 10;
        staticglobal.ball.speed = (float) 0.5;
        staticglobal.getcurrentenemy().kick();
        staticglobal.ball.stuck = false;
        if (staticglobal.getcurrentenemy().rotstate == 1) {
            staticglobal.ball.movex(passlen);
        }
        if (staticglobal.getcurrentenemy().rotstate == 2) {
            staticglobal.ball.movez(-passlen);
        }
        if (staticglobal.getcurrentenemy().rotstate == 3) {
            staticglobal.ball.movex(-passlen);
        }
        if (staticglobal.getcurrentenemy().rotstate == 4) {
            staticglobal.ball.movez(passlen);
        }
    }

    void kick() {
        int passlen = 10;
        staticglobal.ball.speed = (float) 0.5;

        staticglobal.getcurrentenemy().kick();
        staticglobal.ball.stuck = false;
        staticglobal.ball.stuck = false;
        staticglobal.getcurrentplayer().kick();
        if (staticglobal.getcurrentplayer().rotstate == 1) {
            staticglobal.ball.movex(passlen);
        }
        if (staticglobal.getcurrentplayer().rotstate == 2) {
            staticglobal.ball.movez(-passlen);
        }
        if (staticglobal.getcurrentplayer().rotstate == 3) {
            staticglobal.ball.movex(-passlen);
        }
        if (staticglobal.getcurrentplayer().rotstate == 4) {
            staticglobal.ball.movez(passlen);
        }
    }

    void tackle() {
        player p, q;
        p = staticglobal.getcurrentplayer();
        q = staticglobal.getcurrentenemy();
        float x = staticglobal.getdist(p, q);

        if (x < 0.65) {
            staticglobal.getcurrentplayer().kick();
            staticglobal.ball.possesion = true;
        }
    }

    void entackle() {

        player p, q;
        p = staticglobal.getcurrentplayer();
        q = staticglobal.getcurrentenemy();
        float x = staticglobal.getdist(p, q);

        if (x < 0.65) {
            staticglobal.getcurrentenemy().kick();
            staticglobal.ball.possesion = false;
            staticglobal.ball.stuck = false;
        }
    }

    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("entackle", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("passenemy", new KeyTrigger(KeyInput.KEY_O));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Front", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Back", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("i", new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping("k", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("j", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("l", new KeyTrigger(KeyInput.KEY_L));
        inputManager.addMapping("pass", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("tackle", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("stop", new KeyTrigger(KeyInput.KEY_ESCAPE));
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "entackle", "tackle", "pass", "passenemy", "stop");
        inputManager.addListener(analogListener, "Left", "Right", "Front", "Back", "i", "k", "j", "l");

    }

    public void enupdate() {
        if (encntrl.pass == 1 && staticglobal.ball.stuck && !staticglobal.ball.possesion) {
            if (staticglobal.center == false) {
                enpass();
            } else {
                staticglobal.getcurrentenemy().walkleft();
                staticglobal.center = false;
                enpass();
            }
        }
        if (encntrl.tackle == 1 && staticglobal.ball.stuck && staticglobal.ball.possesion && !staticglobal.center) {
            entackle();
        }
        if (encntrl.tackle == 1 && staticglobal.ball.stuck && !staticglobal.ball.possesion && !staticglobal.center) {
            enkick();
        }
        if (encntrl.le == 1 && !staticglobal.center) {
            staticglobal.getcurrentenemy().walkfront();

        }
        if (encntrl.ri == 1 && !staticglobal.center) {
            staticglobal.getcurrentenemy().walkback();

        }
        if (encntrl.down == 1 && !staticglobal.center) {
            staticglobal.getcurrentenemy().walkright();
        }
        if (encntrl.up == 1 && !staticglobal.center) {
            staticglobal.getcurrentenemy().walkleft();
        }
    }
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("stop") && keyPressed) {

            }
            if (name.equals("pass") && keyPressed) {
                if (staticglobal.ball.stuck && staticglobal.ball.possesion) {
                    cntrl.pass = 1;
                    if (staticglobal.center == false) {
                        pass();
                    } else {
                        staticglobal.getcurrentplayer().walkleft();
                        staticglobal.center = false;
                        pass();
                    }
                }
            }

            if (name.equals("passenemy") && keyPressed && staticglobal.ball.stuck && !staticglobal.ball.possesion) {
                if (staticglobal.center == false) {
                    enpass();
                } else {
                    staticglobal.getcurrentenemy().walkleft();
                    staticglobal.center = false;
                    enpass();
                }
            }
            if (name.equals("entackle") && keyPressed && staticglobal.ball.stuck && staticglobal.ball.possesion && !staticglobal.center) {
                entackle();
            }
            if (name.equals("entackle") && keyPressed && staticglobal.ball.stuck && !staticglobal.ball.possesion && !staticglobal.center) {
                enkick();
            }
            if (name.equals("tackle") && keyPressed && staticglobal.ball.stuck && staticglobal.ball.possesion && !staticglobal.center) {
                cntrl.tackle = 1;
                kick();

            }
            if (name.equals("tackle") && keyPressed && staticglobal.ball.stuck && !staticglobal.ball.possesion && !staticglobal.center) {
                cntrl.tackle = 1;

                tackle();

            }

        }
    };

    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (name.equals("Back") && !staticglobal.center) {
                cntrl.le = 1;
                staticglobal.getcurrentplayer().walkback();
            }
            if (name.equals("Front") && !staticglobal.center) {
                cntrl.ri = 1;
                staticglobal.getcurrentplayer().walkfront();

            }
            if (name.equals("Left") && !staticglobal.center) {
                cntrl.up = 1;
                staticglobal.getcurrentplayer().walkleft();
            }
            if (name.equals("Right") && !staticglobal.center) {
                cntrl.down = 1;
                staticglobal.getcurrentplayer().walkright();
            }
            if (name.equals("j") && !staticglobal.center) {
                staticglobal.getcurrentenemy().walkback();
            }
            if (name.equals("l") && !staticglobal.center) {
                staticglobal.getcurrentenemy().walkfront();
            }
            if (name.equals("k") && !staticglobal.center) {
                staticglobal.getcurrentenemy().walkright();
            }
            if (name.equals("i") && !staticglobal.center) {
                staticglobal.getcurrentenemy().walkleft();
            }
        }
    };
}
