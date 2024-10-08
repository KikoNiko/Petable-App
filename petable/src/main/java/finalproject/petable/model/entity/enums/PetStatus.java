package finalproject.petable.model.entity.enums;

public enum PetStatus {
    LOOKING_FOR_A_HOME("Looking for a home"),
    ADOPTED("Adopted"),
    RESERVED("Reserved"),
    IN_VET_CARE("In vet care");

    public final String label;

    private PetStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static PetStatus valueOfLabel(String label) {
        for (PetStatus status : values()) {
            if (status.label.equals(label)) {
                return status;
            }
        }
        return null;
    }
}
