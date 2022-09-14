package com.example.assignment_buiquanghaiph28562.field;

import java.util.Date;

public class khoanchi_class {
    private int id,GD;
    private float chi;

    public khoanchi_class(int id, int GD, float chi) {
        this.id = id;
        this.GD = GD;
        this.chi = chi;
    }

    public khoanchi_class() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGD() {
        return GD;
    }

    public void setGD(int GD) {
        this.GD = GD;
    }

    public float getChi() {
        return chi;
    }

    public void setChi(float chi) {
        this.chi = chi;
    }

}
