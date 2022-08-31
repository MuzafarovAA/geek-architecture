package proxy;

public class SecuredDoor {

    private final Door door;

    public SecuredDoor(Door door) {
        this.door = door;
    }

    public void open(String password) {
        if (authenticate(password)) {
            door.open();
        } else {
            System.out.println("Big no! It ain't possible.");
        }
    }

    public void close() {
        door.close();
    }

    private boolean authenticate(String password) {
        return (password.equals("$ecr@t"));
    }

}
