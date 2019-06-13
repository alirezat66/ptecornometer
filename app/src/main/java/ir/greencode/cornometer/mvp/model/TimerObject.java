package ir.greencode.cornometer.mvp.model;

public class TimerObject {
    int id;
    String title;
    String desc;
    int img;

    public TimerObject(int id, String title, String desc, int img) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getImg() {
        return img;
    }
}
