/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.scene.Node;

class Ball {

    animation ballanimation;
    Node ballnode = new Node();
    int x = 0;
    int z = 0;
    float posx = (float) 0.0, posy = (float) 0.0, posz = (float) 0.0;
    boolean stuck = true;
    boolean possesion = true;
    float speed = (float) 0.1;

    public Ball(animation an) {
        ballanimation = an;

        this.ballnode.attachChild(ballanimation.node);
        staticglobal.pushnode(this.ballnode);
    }

    public void setPosx(float posx) {
        stuck = false;
        this.posx = posx;
    }

    public void setPosz(float posz) {
        stuck = false;
        this.posz = posz;
    }

    void movex(int x) {
        this.x = 0;
        this.x = x;
    }

    void movez(int z) {
        this.z = 0;
        this.z = z;
    }

    void update() {
        if (stuck == false) {
            float t = (float) Math.sqrt((staticglobal.p[staticglobal.currentplayer].posx - staticglobal.ball.posx) * (staticglobal.p[staticglobal.currentplayer].posx - staticglobal.ball.posx) + (staticglobal.p[staticglobal.currentplayer].posz - staticglobal.ball.posz) * (staticglobal.p[staticglobal.currentplayer].posz - staticglobal.ball.posz));
            if (t < 0.19) {

                x = 0;
                z = 0;
                stuck = true;
                possesion = true;
            }
        }

        if (stuck == false) {
            float t = (float) Math.sqrt((staticglobal.p2[staticglobal.currentenemy].posx - staticglobal.ball.posx) * (staticglobal.p2[staticglobal.currentenemy].posx - staticglobal.ball.posx) + (staticglobal.p2[staticglobal.currentenemy].posz - staticglobal.ball.posz) * (staticglobal.p2[staticglobal.currentenemy].posz - staticglobal.ball.posz));
            if (t < 0.19) {

                x = 0;
                z = 0;
                stuck = true;

                possesion = false;
            }
        }
        if (this.x > 0) {
            if (this.posx + speed < 12.4) {
                this.posx += speed;
                x--;
            } else {
                this.posx = (float) 12.4;

                x = 0;
            }
        }
        if (this.x < 0) {

            if (this.posx - speed > -12.4) {
                this.posx -= speed;
                x++;

            } else {
                this.posx = (float) -12.4;
                x = 0;
            }
        }
        if (this.z > 0) {
            if (this.posz + speed < 6.2) {
                this.posz += speed;
                z--;
            } else {
                this.posz = (float) 6.2;
                z = 0;
            }
        }
        if (this.z < 0) {
            if (this.posz - speed > -6.2) {
                this.posz -= speed;
                z++;
            } else {
                this.posz = (float) -6.2;

                z = 0;
            }
        }
        if (stuck) {

            if (possesion) {
                if (posx != staticglobal.p[staticglobal.currentplayer].getballx()) {
                    this.ballanimation.node.rotate(0, 0, -0.1f);
                }
                posx = staticglobal.p[staticglobal.currentplayer].getballx();
                posz = staticglobal.p[staticglobal.currentplayer].getballz();
            } else {
                if (posx != staticglobal.p2[staticglobal.currentenemy].getballx()) {
                    this.ballanimation.node.rotate(0, 0, 0.1f);
                }

                posx = staticglobal.p2[staticglobal.currentenemy].getballx();
                posz = staticglobal.p2[staticglobal.currentenemy].getballz();
            }
        }

        ballnode.setLocalTranslation(posx, posy, posz);
    }
}
