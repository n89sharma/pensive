package Pensive.services;

import Pensive.domainobjects.PatchOperation;
import org.springframework.stereotype.Service;

@Service
public class PensiveAppService {
    public boolean validatePatchOperation(PatchOperation patchOperation) {
        return null != patchOperation.getOp() &&
                null != patchOperation.getPath() &&
                null != patchOperation.getValue() &&
                !patchOperation.getOp().isEmpty() &&
                !patchOperation.getValue().isEmpty() &&
                !patchOperation.getPath().isEmpty();
    }
}
