package main

import (
	"fmt"
	"math"
	"runtime"
	"time"
)

func swap(x, y string) (string, string) {
	return y, x
}

func add(x, y int) int {
	return x + y
}

func transform(x1, x2, x3, x4 int) (y1, y2, y3, y4 int) {
	y1 = x1 + 1
	y2 = x2 + 1
	y3 = x3 * x2
	y4 = x4
	return
}

type Vertex struct {
	X int
	Y int
}

type Prices struct {
	A, B, C float64
}

var m map[string]Prices

var (
	p = Vertex{1, 2}  // has type Vertex
	q = &Vertex{1, 2} // has type *Vertex
	r = Vertex{X: 1}  // Y:0 is implicit
	s = Vertex{}      // X:0 and Y:0
)

func main() {
	fmt.Println("today is:", time.Now().Weekday())
	m = make(map[string]Prices)
	m["key1"] = Prices{5.5, 6.7, 6.6}
	m["key2"] = Prices{5.7, 6.8, 7.7}

	m1 := map[string]Prices{
		"k1": Prices{11.11, 11.12, 11.13},
		"k2": Prices{12.11, 12.12, 12.13},
		"k3": Prices{13.11, 13.12, 13.13},
	}

	fmt.Println("print all map:", m1)
	fmt.Println("key1:", m["key1"], " key2:", m["key2"])

	fmt.Printf("hi %g \n", math.Nextafter(2, 4))
	fmt.Printf("hi %d \n", add(5, 6))
	x1, x2, x3, x4 := transform(1, 2, 3, 4)
	fmt.Printf("print transformation %d,%d,%d,%d \n", x1, x2, x3, x4)

	a, b := swap("hello", "world")
	fmt.Println("print swap strings ", a, b)

	t1, t2 := 5.5, "value"
	fmt.Println("print new type of assignment:", t1, t2)

	sum := 0
	for i := 0; i < 10; i++ {
		sum += i
	}
	fmt.Println("print sum in for loop", sum)

	fmt.Println("print struct:", Vertex{1, 2})
	p := Vertex{1, 2}
	q := &p
	q.X = 1e9
	fmt.Println("print change in struct using pointers:", p)

	fmt.Println("more struct manipulations:", p, q, r, s)

	//override m
	m := make(map[string]int)

	m["Answer"] = 42
	fmt.Println("The value:", m["Answer"])

	m["Answer"] = 48
	fmt.Println("The value:", m["Answer"])

	delete(m, "Answer")
	fmt.Println("The value:", m["Answer"])

	//ok will be set according to the exsistance of Answer in the map
	v, ok := m["Answer"]
	fmt.Println("The value:", v, "Present?", ok)

	//slices
	p1 := []int{2, 3, 5, 7, 11, 13}
	fmt.Println("p ==", p1)
	fmt.Println("p[1:4] ==", p1[1:4])

	// אם אין ערך תחתי, ברירת המחדל היא 0
	fmt.Println("p[:3] ==", p1[:3])

	// אם אין ערך עליון, ברירת המחדל היא האורך של המערך
	fmt.Println("p[4:] ==", p1[4:])

	hypot := func(x, y float64) float64 {
		return math.Sqrt(x*x + y*y)
	}

	fmt.Println("using functions:", hypot(3, 4))

	var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}
	for i, v := range pow {
		fmt.Printf("2**%d = %d\n", i, v)
	}

	pow1 := make([]int, 10)
	for i := range pow1 {
		pow1[i] = 1 << uint(i)
	}
	for _, value := range pow1 {
		fmt.Printf("%d\n", value)
	}

	fmt.Print("Go run on: ")
	switch os := runtime.GOOS; os {
	case "darwin":
		fmt.Println("OS X.")
	case "linux":
		fmt.Println("Linux.")
	default:
		// freebsd, openbsd,
		// plan9, windows...
		fmt.Printf("%s.", os)
	}
}
