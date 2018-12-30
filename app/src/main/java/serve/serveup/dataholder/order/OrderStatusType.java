package serve.serveup.dataholder.order;

public enum OrderStatusType {


    // new order status
    NEW(0),
    // ready order status
    READY(1),
    // finished order status
    FINISHED(2);

    private int status;

    OrderStatusType(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }
}
