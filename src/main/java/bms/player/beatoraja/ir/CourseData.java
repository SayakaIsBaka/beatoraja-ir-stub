package bms.player.beatoraja.ir;

class CourseData {
    public IRCourseData course;
    public IRScoreData score;

    CourseData(IRCourseData crs, IRScoreData scr) {
        this.score = scr;
        this.course = crs;
    }
}
