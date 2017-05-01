/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.scene.Node;

class player {

    animation[] playeranimation = new animation[3];
    Node playernode = new Node();
    boolean stillmode = true;
    float posx = (float) 0.0, posy = (float) 0.0, posz = (float) 0.0;
    float rotx = (float) 0.0, roty = (float) 0.0, rotz = (float) 0.0;
    int rotstate = 1;

    public player(animation an1, animation an2, animation an3) {
        playeranimation[0] = an1;
        playeranimation[1] = an2;
        playeranimation[2] = an3;
        this.playernode.attachChild(an1.node);
        staticglobal.pushnode(this.playernode);
    }

    void changeanimation(int x) {
        if (x != 0) {
            this.setStillmode(false);
        }
        playernode.detachAllChildren();
        playernode.attachChild(playeranimation[x].node);
    }

    public float getPosx() {
        return posx;
    }

    public float getPosy() {
        return posy;
    }

    public float getPosz() {
        return posz;
    }

    public void setPosx(float posx) {
        this.posx = posx;
    }

    public void setPosy(float posy) {
        this.posy = posy;
    }

    public void setPosz(float posz) {
        this.posz = posz;
    }

    public void changeposx(float x) {
        this.posx += x;
        this.changeanimation(1);
    }

    public void changeposy(float y) {
        this.posy += y;
        this.changeanimation(1);
    }

    public void changeposz(float z) {
        this.posz += z;
        this.changeanimation(1);
    }

    public void setRotx(float rotxindegree) {
        rotx += (float) Math.toRadians(rotxindegree);
        playernode.rotate((float) Math.toRadians(rotxindegree), 0.0f, 0.0f);
    }

    public void setRoty(float rotyindegree) {

        roty += (float) Math.toRadians(rotyindegree);
        playernode.rotate(0.0f, (float) Math.toRadians(rotyindegree), 0.0f);
    }

    public void setRotz(float rotzindegree) {

        rotz += (float) Math.toRadians(rotzindegree);
        playernode.rotate(0.0f, 0.0f, (float) Math.toRadians(rotzindegree));
    }

    public void rotto0() {
        playernode.rotate(-rotx, -roty, -rotz);
        rotx = 0;
        roty = 0;
        rotz = 0;

    }

    public void setStillmode(boolean stillmode) {
        this.stillmode = stillmode;
    }

    void update() {
        if (this.stillmode) {
            this.changeanimation(0);
            if (staticglobal.getcurrentplayer() != this) {
                this.rotto0();
                this.rotstate = 1;
                
                
            }
        }
        playernode.setLocalTranslation(posx, posy, posz);
        this.stillmode = true;
    }

    void walkback() {
        this.rotto0();
        this.setRoty(180);
        this.rotstate = 3;

        if (posx > -12.25 && staticglobal.getcollider((int) ((posx - 0.25) * 100), (int) (posz * 100)) == false) {
            this.changeposx((float) -0.05);
        }
        if (posx > -12.25&& !staticglobal.getcollider((int) ((posx - 0.25) * 100), (int) (posz * 100)) == false && staticglobal.getcurrentplayer() != this) {
            this.walkleft();
        }

    }

    void psuedowalkback() {
        this.rotto0();
        this.rotstate = 1;

        if (posx > -12.25 && staticglobal.getcollider((int) ((posx - 0.25) * 100), (int) (posz * 100)) == false) {
            this.changeposx((float) -0.05);
        }
        if (posx > -12.2 && !staticglobal.getcollider((int) ((posx - 0.25) * 100), (int) (posz * 100)) == false && staticglobal.getcurrentplayer() != this) {
            this.walkleft();
        }

    }

    void walkfront() {
        this.rotto0();
        this.rotstate = 1;

        if (posx < 12.25 && staticglobal.getcollider((int) ((posx + 0.25) * 100), (int) (posz * 100)) == false) {
            this.changeposx((float) 0.05);
        }
        if (posx < 12.25 && !staticglobal.getcollider((int) ((posx + 0.25) * 100), (int) (posz * 100)) == false && staticglobal.getcurrentplayer() != this) {
            this.walkleft();
        }
    }

    void walkleft() {

        this.rotto0();
        this.setRoty(90);
        this.rotstate = 2;
        if (posz > -6.1 && staticglobal.getcollider((int) (posx * 100), (int) ((posz - 0.25) * 100)) == false) {
            this.changeposz((float) -0.05);
        }
        if (posz > -6.1 && !staticglobal.getcollider((int) (posx * 100), (int) ((posz - 0.25) * 100)) == false && staticglobal.getcurrentplayer() != this) {
            walkfront();
        }
    }

    void walkright() {
        this.rotto0();
        this.setRoty(-90);
        this.rotstate = 4;
        if (posz < 6.1 && staticglobal.getcollider((int) (posx * 100), (int) ((posz + 0.25) * 100)) == false) {
            this.changeposz((float) 0.05);
        }
        if (posz < 6.1 && !staticglobal.getcollider((int) (posx * 100), (int) ((posz + 0.25) * 100)) == false && staticglobal.getcurrentplayer() != this) {
            walkfront();
        }
    }

    void kick() {
        this.changeanimation(2);
    }

    float getballx() {
        float bd = (float) 0.2;
        if (this.rotstate == 1) {
            return this.posx + bd;
        } else if (this.rotstate == 3) {
            return this.posx - bd;
        } else {
            return this.posx;
        }
    }

    float getballz() {
        float bd = (float) 0.2;
        if (this.rotstate == 4) {
            return this.posz + bd;
        } else if (this.rotstate == 2) {
            return this.posz - bd;
        } else {
            return this.posz;
        }
    }

}
