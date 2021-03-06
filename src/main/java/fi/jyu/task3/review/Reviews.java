package fi.jyu.task3.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fi.jyu.task3.exception.DataNotFoundException;
import fi.jyu.task3.exception.ErrorMessage;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Reviews {

    @XmlElement(name="reviews")
    private List<Review> reviewList;

    private static Map<Integer, Reviews> map = new HashMap<>();
    private static Reviews instance;


    public synchronized static Reviews getInstance(int id){
        instance = map.get(id);
        if(instance == null) {
            instance = new Reviews();
            map.put(id, instance);
            return instance;
        }
        return instance;
    }



    public Reviews() {
        this.reviewList = new ArrayList<Review>();
    }

    public synchronized List<Review> getReviewList() {
    	if (reviewList.isEmpty()){
    		ErrorMessage errorMessage = new ErrorMessage("Not found...", 404, "http://myDocs.org"); 
    		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
    				   throw new NotFoundException(response);
    	}
        return new ArrayList<Review>(reviewList);
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public synchronized void  add(Review c){
    	reviewList.add(c);
    }

}
