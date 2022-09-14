package com.example.assignment_buiquanghaiph28562.field;

public class loaithu_class {
    private String nameKhoanThu;
    int id;

    @Override
    public String toString() {
        return nameKhoanThu;
    }

    public loaithu_class() {
    }

    public loaithu_class(String nameKhoanThu) {
        this.nameKhoanThu = nameKhoanThu;
    }

    public loaithu_class(String nameKhoanThu, int id) {
        this.nameKhoanThu = nameKhoanThu;
        this.id = id;
    }

    public String getNameKhoanThu() {
        return nameKhoanThu;
    }

    public void setNameKhoanThu(String nameKhoanThu) {
        this.nameKhoanThu = nameKhoanThu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
