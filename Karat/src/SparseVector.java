import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseVector {
    private double[] vec;
    public final int length;

    public SparseVector(int size) {
        vec = new double[size];
        length = size;
    }

    // what will happen if set a idx out of boumdary??
    public void set(int idx, double value) {
        if(idx >= 0 && idx < vec.length) // do nothing if idx out of boundary when set
            vec[idx] = value;
    }

    public double get(int idx) {
        if(idx >= vec.length || idx < 0)
            throw new IndexOutOfBoundsException("index out of range");
        return vec[idx];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(double n : vec) {
            sb.append(n).append(", ");
        }
        if(sb.length() > 0) sb.delete(sb.length()-2, sb.length());
        sb.append(']');
        return sb.toString();
    }

    public SparseVector add(SparseVector b) {
        if(this.length != b.length)
            throw new IllegalArgumentException("vector lengths don’t match");

        SparseVector res = new SparseVector(this.length);
        for(int i = 0; i < this.length; i++) {
            res.set(i, vec[i] + b.get(i));
        }
        return res;
    }

    public double dot(SparseVector b) {
        if(this.length != b.length)
            throw new IllegalArgumentException("vector lengths don’t match");

        double res = 0;
        for(int i = 0; i < length; i++) {
            res += vec[i]*b.get(i);
        }
        return res;
    }

    public double cosine(SparseVector b) {
        if(this.length != b.length)
            throw new IllegalArgumentException("vector lengths don’t match");

        return this.dot(b)/(this.norm()*b.norm());
    }

    private double norm(){
        double res = 0;
        for(double n : vec) {
            res += n*n;
        }
        return Math.sqrt(res);
    }
    public static void main(String[] args) {
        SparseVector v1 = new SparseVector(5);
        SparseVector v2 = new SparseVector(5);
        SparseVector v3 = new SparseVector(2);
//        v1.add(v3);
//        v1.dot(v3);
//        try {
//            v1.cosine(v3);
//        } catch (IllegalArgumentException e) {
//            System.out.println("length not equal");
//        }
        v1.set(0, 4.0);
        v1.set(1, 5.0);
        v2.set(1, 2.0);
        v2.set(3, 3.0);
        System.out.println(v1.dot(v2));
        System.out.println(v1.add(v2));
        System.out.println(v1.cosine(v2));
        List<Double> list = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0));
        System.out.println(list.toString());
    }
}


/*
Q1: 設計一個 sparse vector class

sparseVector v = new sparseVector(100); //size constructor; size is 100
    v.set(0, 1.0);.
    v.set(3, 2.0);
    v.set(80,-4.5);

    System.out.println(v.get(80)); //should print -4.5
    System.out.println(v.get(50)); //should print 0.0

    try {
       System.out.println(v.get(101)); //error -- index out of range
       throw new IllegalStateException("We should not get here, an exception should have been thrown");
    } catch (IndexOutOfBoundsException t) {
       // success
    }

    System.out.println(v.toString()); //should print something like [1.0, 0.0, 0.0, 2.0, 0.0, ...]

Q2:

Add these operations to your library: Addition, dot product, and cosine. Formulae for each are provided below; we’re more interested in you writing the code than whether you’ve memorized the formula. For each operation, your code should throw an error if the two input vectors are not equal length.
. 围观我们@1point 3 acres
Sample input/output:
//Note: This is pseudocode. Your actual syntax will vary by language.
v1 = new vector(5). 一亩-三分-地，独家发布
v1[0] = 4.0
v1[1] = 5.0

v2 = new vector(5)
v2[1] = 2.0. 一亩-三分-地，独家发布
v2[3] = 3.0
.留学论坛-一亩-三分地
v3 = new vector(2)

print v1.add(v2) //should print [4.0, 7.0, 0.0, 3.0, 0.0]
print v1.add(v3) //error -- vector lengths don’t match
.1point3acres网
print v1.dot(v2) //should print 10
print v1.dot(v3) //error -- vector lengths don’t match

print v1.cos(v2) //should print 0.433
print v1.cos(v3) //error -- vector lengths don’t match


Formulae:
Addition
a.add(b) = [a[0]+b[0], a[1]+b[1], a[2]+b[2], ...]
.留学论坛-一亩-三分地
Dot product. Waral 博客有更多文章,
a.dot(b) = a[0]*b[0] + a[1]*b[1] + a[2]*b[2] + ...

Cosine
a.cos(b) = a.dot(b) / (norm(a) * norm(b))
//norm(a) = sqrt(a[0]^2 + a[1]^2 + a[2]^2 + ...)
 */
