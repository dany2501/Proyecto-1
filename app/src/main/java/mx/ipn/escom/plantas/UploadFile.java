package mx.ipn.escom.plantas;

public class UploadFile {
    private String imageName;
    private String imageUrl;
    private String name;
    private String type;
    private String age;
    private String seasson;
    private String plantar;


    public UploadFile() {
    }

    public UploadFile(String imageName, String imageUrl, String name, String type, String age, String seasson, String plantar) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.age = age;
        this.seasson = seasson;
        this.plantar = plantar;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSeasson() {
        return seasson;
    }

    public void setSeasson(String seasson) {
        this.seasson = seasson;
    }

    public String getPlantar() {
        return plantar;
    }

    public void setPlantar(String plantar) {
        this.plantar = plantar;
    }
}
