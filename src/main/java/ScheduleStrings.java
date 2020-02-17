public enum ScheduleStrings {
    BY("by"),
    ONE_AM_EST("1:00 AM EST"),
    NINE_AM_FIVE_PM_EST("9:00 AM EST, 5:00 PM EST"),
    NINE_AM_EST("9:00 AM EST"),
    FIVE_PM_EST("5:00 PM EST");

    private String name;

    ScheduleStrings(String name) {
        this.name = name;
    }
}
