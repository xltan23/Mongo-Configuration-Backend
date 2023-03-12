package sg.edu.nus.iss.server.models;

import java.util.Date;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Member {

    // Defining Member attributes
    private String name;
    private String telegram;
    private String grade;

    // Generate getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Convert Member object to JsonObject
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                    .add("name", name)
                    .add("telegram", telegram)
                    .add("grade", grade)
                    .add("date", (new Date()).toString())
                    .build();
    }

    // Convert Member object to Document
    public Document toDoc() {
        Document doc = new Document();
        doc.put("name", name);
        doc.put("telegram", telegram);
        doc.put("grade", grade);
        return doc;
    }

    // Convert Document into Member object
    public static Member create(Document doc) {
        Member member = new Member();
        member.setName(doc.getString("name"));
        member.setTelegram(doc.getString("telegram"));
        member.setGrade(doc.getString("grade"));
        return member;
    }
}
