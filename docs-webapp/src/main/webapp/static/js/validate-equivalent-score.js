function validateRow(definitionKey, equivalentMaxRange, definitionRange, definitionAlternativeRange) {
    var currentMaxRange = -1;
    for (var i = 1; i <= equivalentMaxRange; ++i) {
        var $elem = $("#cell_" + definitionKey + "_" + i);
        var value = $elem.val();
        if (value == "") {
            continue;
        } else {
            if (value.indexOf("-") == -1) {
                var nextValue = validateValueIsOneGreaterThanCurrent(value, currentMaxRange);
                if (nextValue != -1) {
                    currentMaxRange = nextValue;
                } else {
                    return false;
                }
            } else {
                var array = value.split("-");
                if (array.length != 2) {
                    return false;
                }
                var minValue = validateValueIsOneGreaterThanCurrent(array[0], currentMaxRange);
                if (minValue != -1) {
                    var maxValue = validateIfValueIsGreater(array[1], minValue);
                    if (maxValue != -1) {
                        currentMaxRange = maxValue;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
    }
    if (currentMaxRange == definitionRange) {
        return true;
    } else if (definitionAlternativeRange != undefined && currentMaxRange == definitionAlternativeRange) {
        return true;
    } else {
        return false;
    }
}

function validateValueIsOneGreaterThanCurrent(valueToValidate, currentMaxValue) {
    if (isNaN(valueToValidate)) {
        return -1;
    }
    var intValue = parseInt(valueToValidate);
    if (intValue - currentMaxValue != 1) {
        return -1;
    }
    return intValue;
}

function validateIfValueIsGreater(valueToValidate, valueToCompare) {
    if (isNaN(valueToValidate)) {
        return -1;
    }
    var intValue = parseInt(valueToValidate);
    if (intValue > valueToCompare) {
        return intValue;
    }
    return -1;
}