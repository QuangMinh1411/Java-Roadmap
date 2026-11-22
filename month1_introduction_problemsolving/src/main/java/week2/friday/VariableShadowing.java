package week2.friday;

public class VariableShadowing {
    private int score = 0;

    private void setScoreWrong(int score) {
        // Lỗi: tham số che khuất trường; chỉ tự gán tham số cho chính nó.
        score = score;
    }

    private void setScoreFixed(int score) {
        // Sửa: this.score chỉ rõ trường của đối tượng.
        this.score = score;
    }

    public static void main(String[] args) {
        VariableShadowing example = new VariableShadowing();
        example.setScoreWrong(8);
        System.out.println("Sai: score = " + example.score);
        example.setScoreFixed(8);
        System.out.println("Sửa: score = " + example.score);
    }
}
