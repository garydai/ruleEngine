const mm = [
  ['and', '并且'],
  ['or', '或者'],
  ['gt', '大于'],
  ['lt', '小于'],
  ['ge', '大于等于'],
  ['le', '小于等于'],
  ['eq', '等于'],
  ['ne', '不等于'],
  ['true', '是'],
  ['false', '否'],
  ['contains', '包括'],
  ['notcontains', '不包括'],
  ['null', '空值']
]

// arr to obj ,such as { CN : "China", US : "USA" }
const mmm = mm.reduce((acc, cur) => {
  acc[cur[0]] = cur[1]
  acc[cur[1]] = cur[0]
  return acc
}, {})

module.exports = {
  op: [
    'or',
    'and'
  ],
  expressionOp: [
    '>',
    '<',
    '>=',
    '<=',
    '=',
    '!='
  ],
  relationMap: {
    and: '并且',
    or: '或者'
  },
  opMap: [
    {
      'Integer': [
        '大于',
        '小于',
        '大于等于',
        '小于等于',
        '等于',
        '不等于'
      ],
      'Double': [
        '大于',
        '小于',
        '大于等于',
        '小于等于',
        '等于',
        '不等于'
      ],
      'Boolean': [
        '等于'
      ],
      'String': [
        '大于',
        '小于',
        '大于等于',
        '小于等于',
        '等于',
        '不等于'
      ],
      'List.String': [
        '等于',
        '包括',
        '不包括'
      ]
    },
    [
      '并且',
      '或者'
    ]
  ],
  m: mmm,
  ruleVersion: 1
}
