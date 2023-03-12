package sg.edu.nus.iss.server.repositories;

import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.server.models.Member;

@Repository
public class MemberRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    // Save to members collection 
    public ObjectId save(Member member) {
        Document document = mongoTemplate.insert(member.toDoc(), "members");
        return document.getObjectId("_id");
    }

    // Get Member object from members collection by telegram
    public Optional<Member> get(String telegram) {
        Criteria criteria = Criteria.where("telegram").is(telegram);
        Query query = Query.query(criteria);
        Document document = mongoTemplate.findOne(query, Document.class, "members");
        if (null == document) {
            return Optional.empty();
        }
        return Optional.of(Member.create(document));
    }
}
