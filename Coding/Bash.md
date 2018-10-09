# Bash Scripts

## The Basics & Syntax

All `.sh` files contain `#!/bin/bash` as the first line.

To execute files in the terminal, type
```
cd path_to_file
chmod u+x filename.sh; bash filename.sh args...
```

### Variables Syntax
The `*` keyword represents the directory of the current path.

For an array `arr`, an element is called via `${arr[i]}` and the length is given by `${#arr[*]}`. For command line arguments, each argument is referenced by `$i` starting at `i = 1`.

To split a string `str` at a delimiter `delim`, use
```bash
IFS=$'delim' read -r -a array <<< "$str"
```

### Loop Syntax
In bash, loops and conditional statements have the following syntax:

**Foreach Loop**
```bash
for item in location; do
    # actions to be repeated
done
```

**If-Else Statement**
```bash
if [[ condition ]]; then
    # actions if true
else
    # actions if false
fi
```
Appropriate conditions include `-option $var`, `$var1 -eq num`, and `$var1 = $var2`.

**While Loops**
```bash
while action_is_possible; do
    # actions to be repeated
done
```
Most of the time, while loops are used to loop through each line in a file, done via
```bash
while read line; do
    # actions for each line
done < "$1"
```
assuming the filename is the first argument.
