/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

class animation {

    Spatial objectframes[];
    int numberofframes, currentindex = 0;
    boolean visible = true;
    Node node = new Node();

    animation(int numberofframes, Spatial objectframes[]) {
        this.numberofframes = numberofframes;
        this.objectframes = objectframes;
        node.attachChild(this.objectframes[0]);
    }

    animation(int numberofframes, Spatial objectframes[], int size) {
        this.numberofframes = numberofframes;
        this.objectframes = objectframes;
        node.attachChild(this.objectframes[0]);
        node.scale(size);
    }

    Node getcurrentframe() {
        return node;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    void visibilitytoggle() {
        this.stopanimate();
        this.visible = !this.visible;
    }

    void animate() {
        node.detachAllChildren();
        if (this.visible) {

            node.attachChild(this.objectframes[this.currentindex]);
            this.currentindex++;
            this.currentindex %= this.numberofframes;
        }

    }

    void stopanimate() {
        this.currentindex = 0;
    }

}