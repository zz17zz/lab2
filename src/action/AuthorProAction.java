package action;

import com.opensymphony.xwork2.Action;

import domin.Author;
import service.AuthorService;

public class AuthorProAction implements Action {                    
    private Author author;
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    @Override
    public String execute() throws Exception {
        AuthorService au=new AuthorService();
        Integer i =au.validateAuthor(author);
        if (i>0)
            return SUCCESS;
        return ERROR;
    }

}