package Pensive.author;

import Pensive.prototype.PatchActionType;
import Pensive.prototype.PatchOperation;
import Pensive.prototype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class AuthorService extends Service<Author, AuthorValidator, AuthorRepository> {

    private final static String pathOfBookIds = "/bookIds/-";

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorValidator authorValidator) {
        super(authorRepository, authorValidator);
    }

    @Override
    protected void updateDomainObjectForPatchOperation(Author author, PatchOperation patchOperation) {
        if (getValidator().validatePatchOperationForDomainObject(
                pathOfBookIds,
                patchOperation,
                PatchActionType.ADD,
                PatchActionType.REMOVE)) {
            updateListFromPatchOperation(author.getBookIds(), patchOperation);
        }
    }

    public Author deleteAuthor(String lastName) {
        List<Author> authors = getRepository().findByLastName(lastName);
        Author author;
        if (!authors.isEmpty() && authors.size() == 1) {
            author = authors.get(0);
        } else {
            author = new Author();
            author.addError("Deletion by last name must match only one author. The author must exist in the database.");
        }
        return author;
    }

}
