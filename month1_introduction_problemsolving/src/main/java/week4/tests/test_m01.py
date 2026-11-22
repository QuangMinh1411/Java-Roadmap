"""12 ca console, chỉ cần Python 3 và JDK 17+, không dùng thư viện ngoài."""

import json
from pathlib import Path
import shutil
import subprocess
import tempfile


def main():
    week4 = Path(__file__).resolve().parent.parent
    cases = json.loads((week4 / "tests" / "cases.json").read_text(encoding="utf-8"))
    assert len(cases) == 12, "Bộ nộp phải có đúng 12 ca"
    report = ["M01 — Kết quả kiểm thử từ thư mục mới", ""]

    # Chỉ sao chép mã nguồn và bộ kiểm thử; không dùng class/target có sẵn.
    with tempfile.TemporaryDirectory(prefix="m01-clean-") as folder:
        clean = Path(folder)
        shutil.copy2(week4 / "M01Menu.java", clean)
        shutil.copytree(week4 / "tests", clean / "tests")
        subprocess.run(
            ["javac", "--release", "17", "-encoding", "UTF-8", "-d", "out", "M01Menu.java"],
            cwd=clean, check=True, capture_output=True, text=True, encoding="utf-8", timeout=30,
        )
        for case in cases:
            result = subprocess.run(
                ["java", "-Dfile.encoding=UTF-8", "-cp", "out", "week4.M01Menu"],
                input=case["input"], cwd=clean, capture_output=True,
                text=True, encoding="utf-8", timeout=10,
            )
            assert result.returncode == 0, (case["id"], result.stderr)
            assert not result.stderr, (case["id"], result.stderr)
            # Kiểm tra theo thứ tự và số lần, kể cả thông báo lặp lại.
            position = 0
            for fragment in case["expected"]:
                found = result.stdout.find(fragment, position)
                assert found >= 0, (case["id"], fragment, result.stdout)
                position = found + len(fragment)
            for fragment in case.get("absent", []):
                assert fragment not in result.stdout, (case["id"], fragment, result.stdout)
            report.append(f'PASS {case["id"]}: {case["name"]}')
            report.append(f'  Đầu vào: {case["input"]!r}')
            report.append("  Đầu ra đã xác nhận: " + " | ".join(case["expected"]))
            print(f'PASS {case["id"]}: {case["name"]}')

    report.extend(["", "12/12 PASS; biên dịch và chạy từ thư mục tạm mới, không dùng class có sẵn."])
    (week4 / "tests" / "results.txt").write_text("\n".join(report) + "\n", encoding="utf-8")
    print(report[-1])


if __name__ == "__main__":
    main()
