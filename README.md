# Usage

Easy:

```
echo '(is (= {:foo 1 :bar 2} {:foo -1 :bar 2}))' | clojure -Sdeps '{:deps {io.github.pesterhazy/elucidate {:git/sha "924d710f48dc82addbe332261291bf757c5ecc69"}}}' -M -m elucidate.main
```

OK, maybe not so easy. One step at a time. By default, the output of clojure.test assertions can be hard to read:

```
user=> (is (= {:foo 1 :bar 2} {:foo -1 :bar 2}))

FAIL in () (NO_SOURCE_FILE:1)
expected: (= {:foo 1, :bar 2} {:foo -1, :bar 2})
  actual: (not (= {:foo 1, :bar 2} {:foo -1, :bar 2}))
```

OK, so copy what comes after `actual:` to your clipboard. The clipboard should now contain this string:

```
(not (= {:foo 1, :bar 2} {:foo -1, :bar 2}))
```

Now you can pipe the result into `elucidate`:

```
pbpaste | clojure -Sdeps '{:deps {io.github.pesterhazy/elucidate {:git/sha "924d710f48dc82addbe332261291bf757c5ecc69"}}}' -M -m elucidate.main
```

It looks like this:

![screenshot](screenshot.jpg)

# Motivation

By default, the output of clojure.test assertions can be hard to read:

```
user=> (is (= {:foo 1 :bar 2} {:foo -1 :bar 2}))

FAIL in () (NO_SOURCE_FILE:1)
expected: (= {:foo 1, :bar 2} {:foo -1, :bar 2})
  actual: (not (= {:foo 1, :bar 2} {:foo -1, :bar 2}))
```

