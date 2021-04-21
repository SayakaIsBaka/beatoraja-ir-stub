package bms.player.beatoraja.ir;

class PlayData {
    public IRChartData song;
    public IRScoreData score;

    PlayData(IRChartData model, IRScoreData scoreData) {
        this.song = model;
        this.score = scoreData;
    }
}
