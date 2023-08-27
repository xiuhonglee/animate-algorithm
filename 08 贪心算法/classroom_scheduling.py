courses = [
    {'name': 'Algorithms',              'start': 9,  'end': 11  },
    {'name': 'Operating Systems',       'start': 11, 'end': 13  },
    {'name': 'Computer Architecture',   'start': 8,  'end': 10  },
    {'name': 'Software Engineering',    'start': 10, 'end': 12  },
    {'name': 'Database Systems',        'start': 13, 'end': 15  },
    {'name': 'Artificial Intelligence', 'start': 15, 'end': 17  },
    {'name': 'Networks',                'start': 14, 'end': 16  },
    {'name': 'Cryptography',            'start': 16, 'end': 18  },
    {'name': 'Machine Learning',        'start': 17, 'end': 19  },
    {'name': 'Data Science',            'start': 12, 'end': 14  },
]

def schedule(courses):
    # 按照结束时间对课程排序
    sorted_courses = sorted(courses, key=lambda x: x['end'])
    # 初始化结果集
    result = [sorted_courses[0]]
    for i in range(1, len(sorted_courses)):
        # 如果课程的开始时间不冲突，就把它加入结果集
        if sorted_courses[i]['start'] >= result[-1]['end']:
            result.append(sorted_courses[i])
    return result

result = schedule(courses)
for course in result:
    print(course['name'], course['start'], course['end'])