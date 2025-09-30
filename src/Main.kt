import kotlin.math.abs


class PhanSo(private var tu: Int, private var mau: Int) {

    // Nhập phân số từ bàn phím
    fun nhap() {
        do {
            print("Nhập tử số: ")
            tu = readln().toInt()   // nhập tử số
            print("Nhập mẫu số (khác 0): ")
            mau = readln().toInt()  // nhập mẫu số
            if (mau == 0) {
                println("Mẫu số không được bằng 0, vui lòng nhập lại") // mẫu số không được = 0
            }
        } while (mau == 0) // lặp lại nếu mẫu số = 0
    }


    // In phân số ra màn hình

    fun xuat() {
        println("$tu/$mau")
    }


    // Rút gọn phân số (tối giản)

    fun toiGian() {
        val ucln = gcd(abs(tu), abs(mau)) // tính ước chung lớn nhất
        tu /= ucln
        mau /= ucln
        // đưa mẫu số về dương cho đẹp
        if (mau < 0) {
            tu = -tu
            mau = -mau
        }
    }

    // so sánh với phân số khác

    fun soSanh(ps: PhanSo): Int {
        val a = tu * ps.mau      // quy đồng tử số 1
        val b = ps.tu * mau      // quy đồng tử số 2
        return when {
            a < b -> -1
            a == b -> 0
            else -> 1
        }
    }

    // Tính tổng với một phân số khác

    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = tu * ps.mau + ps.tu * mau   // công thức cộng phân số
        val mauMoi = mau * ps.mau
        val kq = PhanSo(tuMoi, mauMoi) // tạo phân số kết quả
        kq.toiGian() // rút gọn trước khi trả về
        return kq
    }


    // Trả về giá trị thực (double)
    // Dùng để so sánh, tính toán nhanh

    fun giaTri(): Double {
        return tu.toDouble() / mau.toDouble()
    }


    // Hàm gcd (ước chung lớn nhất)
    // Dùng để rút gọn phân số

    companion object {
        fun gcd(a: Int, b: Int): Int {
            return if (b == 0) a else gcd(b, a % b)
        }
    }
}

fun main() {
    // 1. Nhập số lượng phân số

    print("Nhập số lượng phân số: ")
    val n = readln().toInt()
    val arr = Array(n) { PhanSo(1, 1) } // tạo mảng phân số

    // 2. Nhập mảng phân số

    for (i in 0 until n) {
        println(" Nhập phân số thứ ${i + 1}:")
        arr[i] = PhanSo(1, 1)
        arr[i].nhap()
    }

    // 3. In mảng phân số vừa nhập

    println("\n Mảng phân số vừa nhập:")
    arr.forEach { it.xuat() }

    // 4. Tối giản từng phân số và in lại

    println("\n Mảng phân số sau khi tối giản:")
    arr.forEach {
        it.toiGian()
        it.xuat()
    }

    // 5. Tính tổng tất cả phân số trong mảng

    var tong = PhanSo(0, 1) // khởi tạo phân số 0/1
    for (ps in arr) {
        tong = tong.cong(ps) // cộng dồn
    }
    println("\n Tổng các phân số:")
    tong.xuat()

    // 6. Tìm phân số lớn nhất trong mảng

    var max = arr[0]
    for (i in 1 until n) {
        if (arr[i].soSanh(max) == 1) {
            max = arr[i]
        }
    }
    print("\n Phân số lớn nhất: ")
    max.xuat()


    // sắp xếp hàm bằng Bubble sort
    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (arr[j].soSanh(arr[j + 1]) == -1) {
                val tmp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = tmp
            }
        }
    }

    println("\n Mảng sau khi sắp xếp giảm dần:")
    arr.forEach { it.xuat() }

}
