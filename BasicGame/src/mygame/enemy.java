/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

class enemy extends player {

    public enemy(animation an1, animation an2, animation an3) {
        super(an1, an2, an3);
    }
void update() {
        if (this.stillmode) {
            this.changeanimation(0);
            if(staticglobal.getcurrentenemy()!=this)
            {
                
        this.rotto0();
        this.rotstate = 3;
            }
        }
        playernode.setLocalTranslation(posx, posy, posz);
        this.stillmode = true;
    }
    @Override
    void walkback() {
        this.rotto0();
        this.rotstate = 3;
        if (posx > -12.25 && staticglobal.getcollider((int) ((posx - 0.25) * 100), (int) (posz * 100)) == false) {
            this.changeposx((float) -0.05);
        }
        if (posx > -12.25 && !staticglobal.getcollider((int) ((posx - 0.25) * 100), (int) (posz * 100)) == false && staticglobal.getcurrentenemy() != this) {
            this.walkright();
        }
    }

    void psuedowalkfront() {
        this.rotto0();
        this.rotstate = 3;
        if (posx < 12.25 && staticglobal.getcollider((int) ((posx + 0.25) * 100), (int) (posz * 100)) == false) {
            this.changeposx((float) 0.05);
        }
        if (posx < 12.25 && !staticglobal.getcollider((int) ((posx + 0.25) * 100), (int) (posz * 100)) == false && staticglobal.getcurrentenemy() != this) {
            this.walkright();
        }

    }

    @Override
    void walkfront() {
        this.rotto0();
        this.setRoty(180);
        this.rotstate = 1;
        if (posx < 12.25 && staticglobal.getcollider((int) ((posx + 0.25) * 100), (int) (posz * 100)) == false) {
            this.changeposx((float) 0.05);
        }
        if (posx < 12.25 && !staticglobal.getcollider((int) ((posx + 0.25) * 100), (int) (posz * 100)) == false && staticglobal.getcurrentenemy() != this) {
            this.walkright();
        }
    }

    @Override
    void walkleft() {

        this.rotto0();
        this.setRoty(-90);
        this.rotstate = 2;
        if (posz > -6.1 && staticglobal.getcollider((int) (posx * 100), (int) ((posz - 0.25) * 100)) == false) {
            this.changeposz((float) -0.05);
        }
        if (posz > -6.1 && !staticglobal.getcollider((int) (posx * 100), (int) ((posz - 0.25) * 100)) == false && staticglobal.getcurrentenemy() != this) {
            walkback();
        }
    }

    @Override
    void walkright() {
        this.rotto0();
        this.setRoty(90);
        this.rotstate = 4;
        if (posz < 6.1 && staticglobal.getcollider((int) (posx * 100), (int) ((posz + 0.25) * 100)) == false) {
            this.changeposz((float) 0.05);
        }
        if (posz < 6.1 && !staticglobal.getcollider((int) (posx * 100), (int) ((posz + 0.25) * 100)) == false && staticglobal.getcurrentenemy() != this) {
            walkback();

        }
    }
}
