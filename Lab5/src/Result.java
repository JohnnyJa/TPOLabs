public class Result {

    public int totalMessages;
    public int totalApproved;
    public int totalRejected;
    public double averageQueueLength;
    public double rejectedPercentage;

    public Result(int totalMessages, int totalApproved, int totalRejected, int sumQueueSize) {
        this.totalMessages = totalMessages;
        this.totalApproved = totalApproved;
        this.totalRejected = totalRejected;
        this.averageQueueLength = (double)sumQueueSize / totalMessages;
        this.rejectedPercentage = (double)totalRejected / totalMessages;
    }
}
