package Pensive.user;

import Pensive.prototype.PatchActionType;
import Pensive.prototype.PatchOperation;
import Pensive.prototype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class UserService extends Service<User, UserValidator, UserRepository> {

    private static final String pathOfBookIds = "/bookIds/-";

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator) {
        super(userRepository, userValidator);
    }

    @Override
    protected void updateDomainObjectForPatchOperation(User user, PatchOperation patchOperation) {
        if (getValidator().validatePatchOperationForDomainObject(
                pathOfBookIds,
                patchOperation,
                PatchActionType.ADD,
                PatchActionType.REMOVE)) {
            List<String> bookIds = null == user.getBookIds() ? new ArrayList<String>() : user.getBookIds();
            updateListFromPatchOperation(bookIds, patchOperation);
            user.setBookIds(bookIds);
        }
    }
}
