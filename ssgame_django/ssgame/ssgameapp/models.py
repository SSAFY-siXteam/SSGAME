# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class TbCategory(models.Model):
    category_seq = models.BigAutoField(primary_key=True)
    category_name = models.CharField(unique=True, max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_category'


class TbGameGenre(models.Model):
    game_seq = models.OneToOneField('TbGameInfo', models.DO_NOTHING, db_column='game_seq', primary_key=True)
    genre_seq = models.ForeignKey('TbGenre', models.DO_NOTHING, db_column='genre_seq')

    class Meta:
        managed = False
        db_table = 'tb_game_genre'
        unique_together = (('game_seq', 'genre_seq'),)


class TbGameInfo(models.Model):
    game_seq = models.BigAutoField(primary_key=True)
    average_forever = models.IntegerField(blank=True, null=True)
    developers = models.CharField(max_length=255, blank=True, null=True)
    game_name = models.CharField(max_length=255)
    header_image = models.CharField(max_length=255, blank=True, null=True)
    is_free = models.TextField()  # This field type is a guess.
    languages = models.TextField(blank=True, null=True)
    movies = models.TextField(blank=True, null=True)
    negative = models.BigIntegerField(blank=True, null=True)
    owners_max = models.BigIntegerField(blank=True, null=True)
    owners_min = models.BigIntegerField(blank=True, null=True)
    platforms = models.CharField(max_length=255, blank=True, null=True)
    positive = models.BigIntegerField(blank=True, null=True)
    price = models.IntegerField(blank=True, null=True)
    publisher = models.CharField(max_length=255, blank=True, null=True)
    release_date = models.TextField(blank=True, null=True)
    screenshots = models.TextField(blank=True, null=True)
    short_description = models.TextField(blank=True, null=True)
    short_description_kr = models.TextField(blank=True, null=True)
    steam_appid = models.BigIntegerField(unique=True)
    website = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_game_info'


class TbGameTag(models.Model):
    game_tag_seq = models.BigAutoField(primary_key=True)
    tag_count = models.BigIntegerField()
    tag_ratio = models.FloatField()
    game_seq = models.ForeignKey(TbGameInfo, models.DO_NOTHING, db_column='game_seq')
    tag_seq = models.ForeignKey('TbTag', models.DO_NOTHING, db_column='tag_seq')

    class Meta:
        managed = False
        db_table = 'tb_game_tag'
        unique_together = (('tag_seq', 'game_seq'),)


class TbGenre(models.Model):
    genre_seq = models.BigAutoField(primary_key=True)
    genre_name = models.CharField(unique=True, max_length=255)
    genre_name_kr = models.CharField(unique=True, max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_genre'


class TbMember(models.Model):
    member_seq = models.BigAutoField(primary_key=True)
    avatar_url = models.CharField(max_length=255)
    created_date = models.DateTimeField(blank=True, null=True)
    email = models.CharField(unique=True, max_length=255)
    game_count = models.BigIntegerField()
    is_deleted = models.TextField()  # This field type is a guess.
    is_public = models.TextField()  # This field type is a guess.
    password = models.CharField(max_length=255)
    ssgame_id = models.CharField(unique=True, max_length=255)
    steam_id = models.CharField(unique=True, max_length=255)
    steam_nickname = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_member'


class TbMemberFrequentGenre(models.Model):
    genre_seq = models.OneToOneField(TbGenre, models.DO_NOTHING, db_column='genre_seq', primary_key=True)
    member_seq = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='member_seq')
    genre_count = models.BigIntegerField()

    class Meta:
        managed = False
        db_table = 'tb_member_frequent_genre'
        unique_together = (('genre_seq', 'member_seq'),)


class TbMemberGameList(models.Model):
    member_game_list_seq = models.BigAutoField(primary_key=True)
    member_game_rating = models.IntegerField()
    member_play_time = models.BigIntegerField()
    game_seq = models.ForeignKey(TbGameInfo, models.DO_NOTHING, db_column='game_seq')
    member_seq = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='member_seq')

    class Meta:
        managed = False
        db_table = 'tb_member_game_list'
        unique_together = (('member_seq', 'game_seq'),)


class TbMemberPreferredCategory(models.Model):
    category_seq = models.OneToOneField(TbCategory, models.DO_NOTHING, db_column='category_seq', primary_key=True)
    member_seq = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='member_seq')

    class Meta:
        managed = False
        db_table = 'tb_member_preferred_category'
        unique_together = (('category_seq', 'member_seq'),)


class TbMemberPreferredTag(models.Model):
    member_tag_seq = models.BigAutoField(primary_key=True)
    preferred_tag_ratio = models.FloatField()
    member_seq = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='member_seq')
    tag_seq = models.ForeignKey('TbTag', models.DO_NOTHING, db_column='tag_seq')

    class Meta:
        managed = False
        db_table = 'tb_member_preferred_tag'
        unique_together = (('member_seq', 'tag_seq'),)


class TbMemberRecommendedGame(models.Model):
    recommended_game_seq = models.BigAutoField(primary_key=True)
    recommended_ratio = models.FloatField()
    game_seq = models.ForeignKey(TbGameInfo, models.DO_NOTHING, db_column='game_seq')
    member_seq = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='member_seq')

    class Meta:
        managed = False
        db_table = 'tb_member_recommended_game'
        unique_together = (('member_seq', 'game_seq'),)


class TbRedarChartInfo(models.Model):
    category_seq = models.OneToOneField(TbCategory, models.DO_NOTHING, db_column='category_seq', primary_key=True)
    member_seq = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='member_seq')
    category_ratio = models.FloatField()

    class Meta:
        managed = False
        db_table = 'tb_redar_chart_info'
        unique_together = (('category_seq', 'member_seq'),)


class TbTag(models.Model):
    tag_seq = models.BigAutoField(primary_key=True)
    tag_name = models.CharField(unique=True, max_length=255)
    tag_name_kr = models.CharField(unique=True, max_length=255)
    category_seq = models.ForeignKey(TbCategory, models.DO_NOTHING, db_column='category_seq', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_tag'
