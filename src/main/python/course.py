class Course:
    def __init__(self, code, name, subject, number, credits, professor, 
                 prereqs, gen_ed, repeat, qtr):
        self.code = code
        self.name = name
        self.subject = subject
        self.number = number
        self.credits = credits
        self.professor = professor
        self.prereqs = prereqs
        self.gen_ed = gen_ed
        self.repeat = repeat
        self.qtr = qtr
        
    def to_dict(self):
        dict = {
            'Code': self.code,
            'Name': self.name,
            'Subject': self.subject,
            'Number': self.number,
            'Credits': self.credits,
            'Professor': self.professor,
            'Gen-Ed': self.gen_ed,
            'Repeatable': self.repeat,
            'Quarters Offered': self.qtr,
        }
        
        if self.prereqs == "N/A": 
            dict["Prereqs"] = self.prereqs
        else:
            dict['Prereqs'] = (', ').join(self.prereqs)
        return dict
    