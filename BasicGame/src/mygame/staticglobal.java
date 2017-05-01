/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.scene.Node;

class staticglobal {

    static void cleanup() {
        initialenposx = new float[8];
        initialenposz = new float[8];
        initialposx = new float[8];
        initialposz = new float[8];
        mygoal = 0;
        enemygoal = 0;
        nodearray = new Node[200];
        currentnodeid = 1;
        animationarray = new animation[200];
        currentanimationid = 1;
        colliderarray = new boolean[3420][2220];
        center = true;
        p = new player[8];
        p2 = new enemy[8];
        currentplayer = 0;
        currentenemy = 0;
        ball = null;
        strt = "";

    }
    static String strt;
    static float initialenposx[] = new float[8];
    static float initialenposz[] = new float[8];

    static float getcurrentenposx(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return staticglobal.initialenposx[i] + staticglobal.ball.posx / 3;
        } else if (i == 6 || i == 7) {
            if (staticglobal.ball.posx < staticglobal.initialenposx[i]) {
                return staticglobal.initialenposx[i] + staticglobal.ball.posx;
            }
            return staticglobal.initialenposx[i] + staticglobal.ball.posx / 2;
        } else {
            if (staticglobal.ball.posx < staticglobal.initialenposx[i]) {
                return (float) (staticglobal.initialenposx[i] + staticglobal.ball.posx / 1.5);
            }
            return staticglobal.initialenposx[i] + staticglobal.ball.posx / 3;
        }

    }

    static float getcurrentenposz(int i) {
        return staticglobal.initialenposz[i];

    }

    static void enemyAIupdate() {
        int i;
        for (i = 0; i < 8; i++) {
            if (staticglobal.p2[i] != staticglobal.getcurrentenemy()) {
                if ((staticglobal.getcurrentenposx(i) - staticglobal.p2[i].posx) > 0.3 || (staticglobal.getcurrentenposx(i) - staticglobal.p2[i].posx) < -0.3) {
                    if (staticglobal.getcurrentenposx(i) < staticglobal.p2[i].posx) {

                        staticglobal.p2[i].walkback();

                    } else if (staticglobal.getcurrentenposx(i) > staticglobal.p2[i].posx) {
                        if (staticglobal.p2[i].posx < 6.1) {

                            staticglobal.p2[i].walkfront();
                        } else {
                            staticglobal.p2[i].psuedowalkfront();
                        }

                    }

                }
            }
        }
        for (i = 0; i < 8; i++) {
            if (staticglobal.p2[i] != staticglobal.getcurrentenemy()) {
                if ((staticglobal.getcurrentenposz(i) - staticglobal.p2[i].posz) > 0.3 || (staticglobal.getcurrentenposz(i) - staticglobal.p2[i].posz) < -0.3) {
                    if (staticglobal.getcurrentenposz(i) > staticglobal.p2[i].posz) {
                        staticglobal.p2[i].walkright();
                    } else if (staticglobal.getcurrentenposz(i) < staticglobal.p2[i].posz) {
                        staticglobal.p2[i].walkleft();
                    }
                }
            }

        }
    }

    //ai setup
    static float initialposx[] = new float[8];
    static float initialposz[] = new float[8];

    static float getcurrentposx(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return staticglobal.initialposx[i] + staticglobal.ball.posx / 3;
        } else if (i == 6 || i == 7) {
            if (staticglobal.ball.posx > staticglobal.initialposx[i]) {
                return staticglobal.initialposx[i] + staticglobal.ball.posx;
            }
            return staticglobal.initialposx[i] + staticglobal.ball.posx / 2;
        } else {
            if (staticglobal.ball.posx > staticglobal.initialposx[i]) {
                return (float) (staticglobal.initialposx[i] + staticglobal.ball.posx / 1.5);
            }
            return staticglobal.initialposx[i] + staticglobal.ball.posx / 3;
        }

    }

    static float getcurrentposz(int i) {

        return staticglobal.initialposz[i];
    }

    static void playerAIupdate() {
        int i;
        for (i = 0; i < 8; i++) {
            if (staticglobal.p[i] != staticglobal.getcurrentplayer()) {
                if ((staticglobal.getcurrentposx(i) - staticglobal.p[i].posx) > 0.3 || (staticglobal.getcurrentposx(i) - staticglobal.p[i].posx) < -0.3) {
                    if (staticglobal.getcurrentposx(i) > staticglobal.p[i].posx) {
                        staticglobal.p[i].walkfront();
                    } else if (staticglobal.getcurrentposx(i) < staticglobal.p[i].posx) {
                        if (staticglobal.p[i].posx > -6.1) {
                            staticglobal.p[i].walkback();
                        } else {
                            staticglobal.p[i].psuedowalkback();
                        }
                    }
                }
            }

        }
        for (i = 0; i < 8; i++) {
            if (staticglobal.p[i] != staticglobal.getcurrentplayer()) {
                if ((staticglobal.getcurrentposz(i) - staticglobal.p[i].posz) > 0.3 || (staticglobal.getcurrentposz(i) - staticglobal.p[i].posz) < -0.3) {
                    if (staticglobal.getcurrentposz(i) > staticglobal.p[i].posz) {
                        staticglobal.p[i].walkright();
                    } else if (staticglobal.getcurrentposz(i) < staticglobal.p[i].posz) {
                        staticglobal.p[i].walkleft();
                    }
                }
            }

        }

    }
    static int mygoal = 0;
    static int enemygoal = 0;

    static animation[] animationarray = new animation[200];
    static int currentanimationid = 1;

    static int lastpushedanimation() {
        return currentanimationid - 1;
    }

    static int pushanimation(animation an) {
        animationarray[currentanimationid++] = an;
        return currentanimationid - 1;
    }

    static Node[] nodearray = new Node[200];
    static int currentnodeid = 1;

    static int pushnode(Node node) {
        nodearray[currentnodeid++] = node;
        return currentnodeid - 1;
    }

    static boolean colliderarray[][] = new boolean[3420][2220];

    static boolean getcollider(int x, int y) {
        return colliderarray[x + 1710][y + 710];
    }

    static void setcollider(int x, int y, boolean b) {
        colliderarray[x + 1710][y + 710] = b;
    }

    static void flushcollider() {
        int i, j;
        for (i = 0; i < 3420; i++) {
            for (j = 0; j < 2220; j++) {
                colliderarray[i][j] = false;
            }
        }
    }

    static void colliderupdate() {
        flushcollider();
        int i, j, k;
        for (i = 0; i < 8; i++) {
            int limit = 20;
            int x = (int) (p[i].getPosx() * 100);
            int z = (int) (p[i].getPosz() * 100);
            for (j = x - limit; j < x + limit; j++) {
                for (k = z - limit; k < z + limit; k++) {
                    setcollider(j, k, true);
                }
            }
        }
        for (i = 0; i < 8; i++) {
            int limit = 20;
            int x = (int) (p2[i].getPosx() * 100);
            int z = (int) (p2[i].getPosz() * 100);
            for (j = x - limit; j < x + limit; j++) {
                for (k = z - limit; k < z + limit; k++) {
                    setcollider(j, k, true);
                }
            }
        }
    }

    static player p[] = new player[8];
    static enemy p2[] = new enemy[8];
    static int currentplayer = 0, currentenemy = 0;

    public static void setCurrentplayer(int currentplayer) {
        staticglobal.currentplayer = currentplayer;
    }

    public static void setCurrentenemy(int currentenemy) {
        staticglobal.currentenemy = currentenemy;
    }

    static player getcurrentplayer() {
        return p[staticglobal.currentplayer];
    }

    static enemy getcurrentenemy() {
        return p2[staticglobal.currentenemy];
    }

    static Ball ball;

    static void currentplayerupdate() {
        int i;
        staticglobal.currentplayer = 0;
        float x = (float) Math.sqrt((staticglobal.p[staticglobal.currentplayer].posx - ball.posx) * (staticglobal.p[staticglobal.currentplayer].posx - ball.posx) + (staticglobal.p[staticglobal.currentplayer].posz - ball.posz) * (staticglobal.p[staticglobal.currentplayer].posz - ball.posz));
        for (i = 1; i < 8; i++) {

            float y = (float) Math.sqrt((staticglobal.p[i].posx - ball.posx) * (staticglobal.p[i].posx - ball.posx) + (staticglobal.p[i].posz - ball.posz) * (staticglobal.p[i].posz - ball.posz));
            if (y < x) {
                x = y;
                currentplayer = i;
            }
        }
    }

    static void currentenemyupdate() {
        int i;
        staticglobal.currentenemy = 0;
        float x = (float) Math.sqrt((staticglobal.p2[staticglobal.currentenemy].posx - ball.posx) * (staticglobal.p2[staticglobal.currentenemy].posx - ball.posx) + (staticglobal.p2[staticglobal.currentenemy].posz - ball.posz) * (staticglobal.p2[staticglobal.currentenemy].posz - ball.posz));
        for (i = 1; i < 8; i++) {

            float y = (float) Math.sqrt((staticglobal.p2[i].posx - ball.posx) * (staticglobal.p2[i].posx - ball.posx) + (staticglobal.p2[i].posz - ball.posz) * (staticglobal.p2[i].posz - ball.posz));
            if (y < x) {
                x = y;
                currentenemy = i;
            }
        }
    }

    static float getdist(player p1, player p2) {
        return (float) Math.sqrt((p1.getPosx() - p2.getPosx()) * (p1.getPosx() - p2.getPosx()) + (p1.getPosz() - p2.getPosz()) * (p1.getPosz() - p2.getPosz()));
    }

    static player nearestplayer(player p1) {
        player temp;
        float dist;
        if (p1 != staticglobal.p[0]) {
            temp = staticglobal.p[0];
            dist = getdist(p1, staticglobal.p[0]);
        } else {
            temp = staticglobal.p[1];
            dist = getdist(p1, staticglobal.p[1]);

        }
        int i;
        for (i = 0; i < 8; i++) {
            float disttemp;
            if (staticglobal.p[i] != p1) {
                disttemp = getdist(staticglobal.p[i], p1);
                if (disttemp < dist) {
                    temp = staticglobal.p[i];
                    dist = disttemp;
                }
            }
        }
        return temp;
    }
    static boolean center = true;
}
