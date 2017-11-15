public class LineFileStat {
    private String name;
    private int length;
    private int averageWordLength;
    private String shortestWord;
    private String longestWord;

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(int averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public void setShortestWord(String shortestWord) {
        this.shortestWord = shortestWord;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    @Override
    public String toString() {
        return "LineFileStat{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", averageWordLength=" + averageWordLength +
                ", shortestWord='" + shortestWord + '\'' +
                ", longestWord='" + longestWord + '\'' +
                '}';
    }
}
