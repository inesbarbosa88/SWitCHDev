package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class AccessMethodID implements DomainId {
    private final UUID _accessMethodId;

    public AccessMethodID() {
        this._accessMethodId = UUID.randomUUID();
    }

    public AccessMethodID(UUID accessMethodId) {
        this._accessMethodId = accessMethodId;
    }

    @Override
    public boolean equals (Object objectToCompare){
        if (this == objectToCompare) return true;

        if(objectToCompare instanceof AccessMethodID that){
            return this._accessMethodId.equals(that._accessMethodId);
        }

        return false;
    }

    @Override
    public String toString(){
        return this._accessMethodId.toString();
    }

    public UUID getAccessMethodID() {
        return _accessMethodId;
    }

}
