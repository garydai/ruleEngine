export function clone(obj) {
  var c = obj instanceof Array ? [] : {}
  for (var i in obj) {
    if (obj.hasOwnProperty(i)) {
      var prop = obj[i]
      if (typeof prop === 'object') {
        if (prop instanceof Array) {
          c[i] = []
          for (var j = 0; j < prop.length; j++) {
            if (typeof prop[j] !== 'object') {
              c[i].push(prop[j])
            } else {
              c[i].push(clone(prop[j]))
            }
          }
        } else {
          c[i] = clone(prop)
        }
      } else {
        c[i] = prop
      }
    }
  }
  return c
}

export function walkTree(element, arr) {
  if (element.type === 'rule') {
    arr.push(element.label)
    for (var i = 0; i < element.children.length; i++) {
      walkTree(element.children[i], arr)
    }
  }
}
